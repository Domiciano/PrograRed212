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
                                                        <div class="col-text centText pad-5">
                                                            <div class ="col-1">
                                                                <label for="">Nombre</label>
                                                                <p class="mb-0">${this.card.planName}</p>
                                                                <label for="">Fecha</label>
                                                                <p class="mb-0">${this.card.memEndDate}</p>
                                                                <label for="">Estado</label>
                                                                <p class="mb-0">${this.card.status}</p>
                                                                <label for="">Fecha</label>
                                                                <p class="mb-0">${this.card.memEndDate}</p>
                                                                <label for="">Descuento</label>
                                                                <p class="mb-0">${this.card.client.membershipID.discout}</p>
                                                            </div>
                                                            <div class ="col-2">
                                                                <label for="">Precio</label>
                                                                <p class="mb-0">${this.card.client.membershipID.amount}</p>
                                                                <label for="">Clientes</label>
                                                                <p class="mb-0">${this.card.client}</p>
                                                                <label for="">Ciudad</label>
                                                                <p class="mb-0">${this.card.client.membershipID.venueID.name}</p>
                                                            </div>
                                                            <button>Borrar</button>
                                                            <button>Desabilitar</button>
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