"use strict";

function authHeaders() {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("You are not authenticated");
        window.location.href = "/login.html";
        throw new Error("No JWT token");
    }

    return {
        "Authorization": "Bearer " + token,
        "Content-Type": "application/json"
    };
}

const API = "http://localhost:8080/cars";

/* ===== Navigation ===== */
function show(id) {
    document.querySelectorAll(".card").forEach(c => c.classList.add("hidden"));
    document.getElementById(id).classList.remove("hidden");

    document.querySelectorAll(".menu-btn").forEach(b => b.classList.remove("active"));
    event.target.classList.add("active");
}

/* ===== Burger ===== */
function toggleBurger() {
    const menu = document.getElementById("burgerMenu");
    menu.style.display = menu.style.display === "block" ? "none" : "block";
}

/* ===== Helper ===== */
function row(c) {
    return `
        <tr>
            <td>${c.id}</td>
            <td>${c.brand}</td>
            <td>${c.model}</td>
            <td>${c.year}</td>
            <td>${c.price}</td>
            <td><span class="status ${c.status}">${c.status}</span></td>
        </tr>
    `;
}

/* ===== API ===== */
async function searchCars() {
    const params = new URLSearchParams();

    if (sBrand.value) params.append("brand", sBrand.value);
    if (sModel.value) params.append("model", sModel.value);
    if (sMaxPrice.value) params.append("maxPrice", sMaxPrice.value);

    params.append("pageNumber", 0);
    params.append("pageSize", 10);

    const url = `${API}?${params.toString()}`;

    console.log("SEARCH URL:", url);

    const res = await fetch(url, {
        headers: authHeaders()
    });

    if (!res.ok) {
        console.log(await res.text());
        alert("Search failed: " + res.status);
        return;
    }

    const cars = await res.json();

    searchTable.innerHTML = "";
    cars.forEach(c => searchTable.innerHTML += row(c));
}
async function createCar() {
    const body = {
        brand: cBrand.value,
        model: cModel.value,
        color: cColor.value,
        year: +cYear.value,
        price: +cPrice.value,
        rentPricePerDay: +cRent.value
    };

    await fetch(API, {
        method: "POST",
        headers: authHeaders(),
        body: JSON.stringify(body)
    });

    alert("Car created");
}

async function getAllCars() {
    const res = await fetch(`${API}/get-all`, {
        headers: authHeaders()
    });

    if (!res.ok) {
        const errorText = await res.text();
        console.error("Backend error:", errorText);
        alert("Failed to load cars: " + res.status);
        return;
    }

    const cars = await res.json();

    if (!Array.isArray(cars)) {
        console.error("Expected array, got:", cars);
        alert("Backend returned invalid data");
        return;
    }

    allTable.innerHTML = "";
    cars.forEach(c => allTable.innerHTML += row(c));
}

async function getByModel() {
    const res = await fetch(`${API}/${modelSearch.value}`, {
        headers: authHeaders()
    });
    const c = await res.json();

    modelTable.innerHTML = row(c);
}

async function updateCar() {
    await fetch(`${API}/${uId.value}`, {
        method: "PUT",
        headers: authHeaders(),
        body: JSON.stringify({
            price: +uPrice.value,
            rentPricePerDay: +uRent.value
        })
    });

    alert("Updated");
}

async function deleteCar() {
    await fetch(`${API}/${dId.value}`, {
        method: "DELETE",
        headers: authHeaders()
    });

    alert("Deleted");
}

function logout() {
    localStorage.removeItem("token");
    window.location.href = "login/login.html";
}