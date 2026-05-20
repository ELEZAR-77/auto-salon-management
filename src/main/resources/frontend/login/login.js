const API = "http://localhost:8080";

async function login() {
    const login = document.getElementById("login").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${API}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ login, password })
    });

    if (!res.ok) {
        alert("Login failed");
        return;
    }

    const data = await res.json();
    localStorage.setItem("token", data.token);

    // 🔑 Проверяем куда пускать
    redirectByRole();
}

function parseJwt(token) {
    const base64 = token.split('.')[1]
        .replace(/-/g, '+')
        .replace(/_/g, '/');

    return JSON.parse(atob(base64));
}

function redirectByRole() {
    const token = localStorage.getItem("token");
    const payload = parseJwt(token);

    const role = payload.role;

    if (role === "ADMIN") {
        window.location.href = "/cars-admin.html";
    } else if (role === "EMPLOYEE") {
        window.location.href = "/employee-panel/employee.html";
    } else {
        alert("Unknown role: " + role);
    }
}

function authHeader() {
    return {
        "Authorization": "Bearer " + localStorage.getItem("token")
    };
}