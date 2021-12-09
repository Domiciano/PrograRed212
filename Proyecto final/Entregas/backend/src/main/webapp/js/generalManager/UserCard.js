class UserCard {

    constructor(userCard) {
        this.card = userCard;
        Object.seal(this);
    }

    render = (container) => {
        //console.log(this.card.status)
        //if(this.card.status == "In"){
        let div = document.createElement("div");
        //let day = daysLeft(this.card.memEndDate);
        //let html = "";

        let html = `<div id="${this.card.user.id}" class="card mb-4 py-3 onHover cardSelStyle">
                        <div class="card-body">
                            <div class="row">
                                <div class="column centIcon">
                                    <div class="icons">
                                        <i class="fas fa-arrow-circle-left fa-5x icon-hover"></i>
                                        <i class="fas fa-user-circle fa-5x icon-default"></i>
                                    </div>
                                </div>
                                <div class="column">
                                    <h3 class="h3 mb-0 black font-weight-bold">J${this.card.user.name}</h3>
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
                            <div class="col-text centText pad-5">
                                <h3 class="h3 mb-0 black font-weight-bold">J${this.card.user.name}</h3>
                                <p class="mb-0">Venue: ${this.card.venueName}</p>
                                <p class="mb-0">City: ${this.card.cityName}</p>
                                <small class="mb-0">${this.card.status}</small>
                            </div>
                            <div id="colcheck" class="col-check centIcon">
                                <h1 class="h1check">
                                    <i class="fas fa-check fa-3x check"></i>
                                </h1>
                            </div>  
                        </div>
                    </div>
                    <p class="seeDet mb-1">See details <i class="fas fa-chevron-right pl-3"></i></p>
                </div>`;
        });
    }
    // }
}