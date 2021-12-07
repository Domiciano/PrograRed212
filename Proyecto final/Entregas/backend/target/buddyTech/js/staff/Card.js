class Card{

    constructor(card){
      this.card = card;
      Object.seal(this);
    }

    render = (container) =>{
        if(this.card.client.status == "In"){
            let div = document.createElement("div");

            let day = daysLeft(this.card.memEndDate);
            let html = "";
            
            html = `<div id="${this.card.client.id}" class="card mb-4 py-3">
                        <div class="card-body">
                            <div class="row">
                                <div class="column izq">
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
                cardSelected.innerHTML = card;
            });
        }
    }
}