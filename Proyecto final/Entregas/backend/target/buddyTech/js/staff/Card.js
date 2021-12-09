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
            
            html = `<div id="${this.card.client.id}" class="card mb-4 py-3 onHover cardSelStyle border-left-success">
                        <div class="card-body">
                            <div class="row">
                                <div class="column centIcon">
<<<<<<< HEAD
                                    <div class="icons">
                                        <i class="fas fa-arrow-circle-left fa-5x icon-hover"></i>
                                        <i class="fas fa-user-circle fa-5x icon-default"></i>
                                    </div>
=======
                                <i class="fas fa-user-circle fa-5x"></i>
>>>>>>> 42231b67ba07e1432a2f09e5deae6c87bb125bf3
                                </div>
                                <div class="column">
                                    <h3 class="h3 mb-0 black font-weight-bold">J${this.card.client.name}</h3>
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
<<<<<<< HEAD
                                                            <p class="mb-0">Days left ${day}</p>
                                                            <small class="mb-0">Status ${this.card.status}</small>
                                                        </div>
                                                        <div id="colcheck" class="col-check centIcon">
                                                            <h1 class="h1check">
                                                                <i class="fas fa-check fa-3x check"></i>
=======
                                                            <p class="mb-0">${day}</p>
                                                            <small class="mb-0">${this.card.status}</small>
                                                        </div>
                                                        <div id="colcheck" class="col-check centIcon">
                                                            <h1 class="h1check">
                                                                <i class="fas fa-check fa-4x check"></i>
>>>>>>> 42231b67ba07e1432a2f09e5deae6c87bb125bf3
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