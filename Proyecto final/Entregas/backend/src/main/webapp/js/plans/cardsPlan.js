class cardsPlan{
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
                                      <div class="icons">
                                          <i class="fas fa-arrow-circle-left fa-5x icon-hover"></i>
                                          <i class="fas fa-user-circle fa-5x icon-default"></i>
                                      </div>
                                  </div>
                                  <div class="column">
                                      <h3 class="h3 mb-0 black font-weight-bold">J${this.card.client.name}</h3>
                                      <p class="mb-0">Plan: ${this.card.planName}</p>
                                      <p class="mb-0">Days left ${day}</p>
                                      <button background-color: Transparent; border: none>Ver detalle</button>
                                  </div>
                              </div>
                          </div>
                      </div>`
              div.innerHTML = html;
          }
      }
}