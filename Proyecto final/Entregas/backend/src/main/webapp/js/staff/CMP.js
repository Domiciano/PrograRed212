class CMP{

    constructor(client, membership, plan){
      this.client = client;
      this.membership = membership;
      this.plan = plan;
      Object.seal(this);
    }

    render = (container) =>{
        let div = document.createElement("div");

        let day = daysLeft(this.membership.endDate);

        if(day > 0){
            let html = `<div class="card mb-4 py-3 border-left-success">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="column izq">
                                        <i class="fas fa-user-circle fa-5x"></i>
                                        </div>
                                        <div class="column">
                                            <h3 class="h3 mb-0 black font-weight-bold">${this.client.name}</h3>
                                            <p class="mb-0">Plan: ${this.plan.name}</p>
                                            <p class="mb-0">Days left ${day}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>`
        } else{
            let html = `<div class="card mb-4 py-3 border-left-warning">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="column izq">
                                        <i class="fas fa-user-circle fa-5x"></i>
                                        </div>
                                        <div class="column">
                                            <h3 class="h3 mb-0 black font-weight-bold">${this.client.name}</h3>
                                            <p class="mb-0">Plan: ${this.plan.name}</p>
                                            <p class="mb-0">Days left ${day}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>`
        }
        div.innerHTML = html;
        let card = div.firstChild;                
        container.appendChild(card);
    }
}