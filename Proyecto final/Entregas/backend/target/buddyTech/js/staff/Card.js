class Card {

    constructor(card) {
        this.card = card;
        Object.seal(this);
    }

    render = (container) => {
        if (this.card.status == "In") {
            let div = document.createElement("div");

            let day = daysLeft(this.card.memEndDate);
            let html = "";

            html = `<div id="${this.card.client.id}" class="card mb-4 py-3 onHover cardSelStyle border-left-success">
                        <div class="card-body">
                            <div class="row">
                                <div class="column centIcon">
                                    <div class="icons">
                                        <i class="fas fa-arrow-circle-left fa-5x icon-hover"></i>
                                        <i class="fas fa-user-circle fa-5x icon-default"></i>
                                    </div>
                                </div>
                                <div class="column">
                                    <h3 class="h3 mb-0 black font-weight-bold">${this.card.client.name}</h3>
                                    <p class="mb-0">Plan: ${this.card.planName}</p>
                                    <p class="mb-0">Days left ${day}</p>
                                </div>
                            </div>
                        </div>
                    </div>`
            div.innerHTML = html;

            let cardComplete = div.firstChild;
            container.appendChild(cardComplete);

            var cardSelected = document.getElementById("cardSelected");
            var details = document.getElementById("details");

            cardComplete.addEventListener("click", () => {
                let day = daysLeft(this.card.memEndDate);
                details.innerHTML = "";
                cardSelected.innerHTML = `<div class="card cardStyle mb-5 ml-5">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-user centIcon pad-icon">
                                                        <i class="fas fa-user-circle fa-8x"></i>
                                                        </div>
                                                        <div class="col-text centText pad-5">
                                                            <h3 class="h3 mb-3 black font-weight-bold">${this.card.client.name}</h3>
                                                            <p class="mb-0">${this.card.planName}</p>
<<<<<<< HEAD
                                                            <p class="mb-0">Días restantes ${day}</p>
                                                            <small class="mb-0">Estado ${this.card.status}</small>
=======
                                                            <p class="mb-0">Days left ${day}</p>
                                                            <small class="mb-0">Status ${this.card.status}</small>
>>>>>>> 4d7be6e275a0b6c0650260ef73c1392a41f9ca39
                                                        </div>
                                                        <div id="colcheck" class="col-check centIcon">
                                                            <h1 class="h1check">
                                                                <i class="fas fa-check fa-3x check"></i>
                                                            </h1>
                                                        </div>  
                                                    </div>
                                                </div>
                                                <button id="${this.card.client.id}" class="seeDet">
                                                    <details class="mb-1">
                                                        <summary>
                                                            Ver detalles
                                                            <i class="fas fa-angle-down"></i>
                                                        </summary>
                                                    </details>
                                                </button>
                                            </div>`;

                var button = cardSelected.querySelector(`button[id="${this.card.client.id}"]`);

                button.addEventListener("click", () => {
                    if (details.innerHTML == "") {
                        details.innerHTML = `
                                                <div class="box-shadow effect">
                                                                        <div class="row">
                                                                            <div class="col-details">
                                                                                <h5>Nombre</h5>
                                                                                <p>${this.card.client.name}</p>
                                                                            </div>
                                                                            <div class="col-details">
                                                                                <h5>Apellido</h5>
                                                                                <p>${this.card.client.lastname}</p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-details">
                                                                                <h5>Cédula</h5>
                                                                                <p>${this.card.client.natId}</p>
                                                                            </div>
                                                                            <div class="col-details">
                                                                                <h5>Plan</h5>
                                                                                <p>${this.card.planName}</p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row">
                                                                            <div class="col-details">
                                                                                <h5>Altura</h5>
                                                                                <p>${this.card.client.height}</p>
                                                                            </div>
                                                                            <div class="col-details">
                                                                                <h5>Peso</h5>
                                                                                <p>${this.card.client.weight}</p>
                                                                            </div>
                                                                        </div>
                                                                        <div class="row-options">
                                                                            <div class="col-options">
                                                                                <a class="btn detailDelete">Delete</a>
                                                                                <a class="btn detailBan">Ban</a>
                                                                            </div>
                                                                        </div>
                                                                    </div>`

                        var deleteBtn = details.querySelector(".detailDelete");
                        var banBtn = details.querySelector(".detailBan");

                        deleteBtn.addEventListener("click", async() => {
                            let response = await fetch("http://localhost:8080/backend/api/cls/deleteClient/" + this.card.client.natId, {
                                method: "DELETE",
                                headers: { "Content-Type": "application/json" },
                            });
                            let data = await response.json();
                            console.log(data);
                            details.innerHTML = "";
                            cardSelected.innerHTML = "";
                            getCardInfo();

                        });

                        banBtn.addEventListener("click", async() => {
                            let response = await fetch("http://localhost:8080/backend/api/cls/editclientstatusbyid/" + this.card.client.natId + "/" + 1, {
                                method: "PUT",
                                headers: { "Content-Type": "application/json" },
                            });
                            let data = await response.json();
                            console.log(data);
                            details.innerHTML = "";
                            cardSelected.innerHTML = "";
                            getCardInfo();
                        });

                    } else details.innerHTML = "";
                });
            });




        }
    }
}