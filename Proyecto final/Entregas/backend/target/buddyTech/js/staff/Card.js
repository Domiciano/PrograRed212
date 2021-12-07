class Card{

    constructor(card){
      this.card = card;
      Object.seal(this);
    }

    render = (container) =>{
        console.log(this.card.status)
        if(this.card.status == "In"){
            let div = document.createElement("div");

            let day = daysLeft(this.card.memEndDate);
            let html = "";
            
            html = `<div id="${this.card.client.id}" class="card mb-4 py-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="column centIcon">
                                <i class="fas fa-user-circle fa-5x"></i>
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
            if(day > 0){
            let change = div.querySelector(`div[id="${this.card.client.id}" ]`);
            change.classList.add("border-left-success");
            } else{
                let change = div.querySelector(`div[id="${this.card.client.id}" ]`);
                change.classList.add("border-left-warning");
            }
            
            let cardComplete = div.firstChild;                
            container.appendChild(cardComplete);

            var cardSelected = document.getElementById("cardSelected");

            cardComplete.addEventListener("click", ()=>{
                let day = daysLeft(this.card.memEndDate);
                cardSelected.innerHTML = `<div class="card cardStyle mb-5 ml-5">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-user centIcon pad-icon">
                                                        <i class="fas fa-user-circle fa-8x"></i>
                                                        </div>
                                                        <div class="col-text centText pad-5">
                                                            <h3 class="h3 mb-3 black font-weight-bold">${this.card.client.name}</h3>
                                                            <p class="mb-0">${this.card.planName}</p>
                                                            <p class="mb-0">${day}</p>
                                                            <small class="mb-0">${this.card.status}</small>
                                                        </div>
                                                        <div id="colcheck" class="col-check centIcon">
                                                            <h1 class="h1check">
                                                                <i class="fas fa-check fa-4x check"></i>
                                                            </h1>
                                                        </div>  
                                                    </div>
                                                </div>
                                                <p class="seeDet mb-1">See details <i class="fas fa-chevron-right pl-3"></i></p>
                                            </div>`;
            });
        }
    }
}