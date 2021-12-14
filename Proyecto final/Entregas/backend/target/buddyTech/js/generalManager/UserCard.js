class UserCard {

    constructor(userCard) {
        this.card = userCard;
        Object.seal(this);
    }

    render = (container) => {
        let div = document.createElement("div");


        let html = `<div id="${this.card.user.id}" class="card mb-4 py-3 cardOnHover cardSelStyle">
                        <div class="card-body">
                            <div class="row">
                                <div class="column centIcon">
                                    <div class="icons">
                                        <i class="fas fa-arrow-circle-left fa-5x icon-hover"></i>
                                        <i class="fas fa-user-circle fa-5x icon-default"></i>
                                    </div>
                                </div>
                                <div class="column">
                                    <h3 class="h3 mb-0 black font-weight-bold">${this.card.user.name}</h3>
                                    <p class="mb-0">Venue: ${this.card.venueName}</p>
                                    <p class="mb-0">City: ${this.card.cityName}</p>
                                </div>
                            </div>
                        </div>
                    </div>`
        div.innerHTML = html;
        let cardComplete = div.firstChild;
        container.appendChild(cardComplete);

        var cardSelected = document.getElementById("cardSelected");

        cardComplete.addEventListener("click", () => {
            cardSelected.innerHTML =
                `<div class="card cardStyle mb-5 ml-5">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-user centIcon pad-icon">
                                <i class="fas fa-user-circle fa-8x"></i>
                            </div>
                            <div class="col-text centText pad-5 mx-4">
                                <h3 class="h3 mb-0 black font-weight-bold">${this.card.user.name} ${this.card.user.lastName}</h3>
                                <p class="mb-0">ID: ${this.card.user.id}</p>
                                <p class="mb-0">Sede: ${this.card.venueName}</p>
                                <p class="mb-0">Ciudad: ${this.card.cityName}</p>
                            </div>
                            <div id="colcheck" class="col-check centIcon">
                                <h2 class="h2check ml-5">
                                    <i class="fas fa-check fa-3x check"></i>
                                </h2>
                            </div>  
                            <a id="delete${this.card.user.id}btn" class="btn btn-danger btn-circle btn-lg mt-4 ml-3">
                                <i class="fas fa-trash"></i>
                            </a>                                    
                        </div>
                    </div>   
                </div>`;
            let deleteBtn = document.getElementById("delete"+this.card.user.id+"btn");
            deleteBtn.addEventListener("click", async (e) => {
                e.preventDefault();
                let response = await fetch("http://localhost:8080/backend/api/users/"+this.card.user.id,
                    {
                        method: "DELETE",
                    }
                );
                if (response.ok) {
                    await getCardInfo();
                }
            })
        });
    }
    // }
}