class cardsPlan{
    constructor(plan){
        this.plan = plan;
        Object.seal(this);
      }
  
      render = (container) =>{
          console.log(this.plan.status)
          
              let div = document.createElement("div");
              let html = "";
              
              html = `<div id="${this.plan.name}" class="card mb-4 py-3 cardOnHover cardSelStyle border-left-success">
                          <div class="card-body">
                              <div class="row">
                                  <div class="column centIcon">
                                      <div class="icons">
                                          <i class="fas fa-arrow-circle-left fa-5x icon-hover"></i>
                                          <i class="fas fa-user-circle fa-5x icon-default"></i>
                                      </div>
                                  </div>
                                  <div class="column">
                                      <h3 class="h3 mb-0 black font-weight-bold">J${this.plan.amount}</h3>
                                      <p class="mb-0">Plan: ${this.plan.time}</p>
                                  </div>
                              </div>
                          </div>
                      </div>`
              div.innerHTML = html;
              let cardComplete = div.firstChild;
              container.appendChild(cardComplete);
              
              var cardSelected = document.getElementById("cardsContainer");
              cardComplete.addEventListener("click",()=>{
                   cardSelected.innerHTML =
                   `<div class="card cardStyle mb-5 ml-5">
                   <div class="card-body">
                       <div class="row">
                           <div class="col-user centIcon pad-icon">
                               <i class="fas fa-user-circle fa-8x"></i>
                           </div>
                           <div class="col-text centText pad-5 mx-4">
                               <h3 class="h3 mb-0 black font-weight-bold">${this.plan.name}</h3>
                               <p class="mb-0">Precio: ${this.plan.amount}</p>
                               <p class="mb-0">Fecha: ${this.plan.time}</p>
                               <p class="mb-0">Estado: ${this.plan.status}</p>
                           </div>
                           <div id="colcheck" class="col-check centIcon">
                               <h2 class="h2check ml-5">
                                   <i class="fas fa-check fa-3x check"></i>
                               </h2>
                           </div>  
                       </div>
                   </div>
                   
               </div>`;
              }); 

      }
}