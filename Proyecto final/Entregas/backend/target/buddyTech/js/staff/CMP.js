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

        let html = `<div class="card mb-4 py-3 border-left-primary">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col">
                                        
                                        </div>
                                        <div class="col">
                                            <h3 class="h3 mb-0 black font-weight-bold">${this.client.name}</h3>
                                            <p class="mb-0">${this.plan.name}</p>
                                            <p class="mb-0">${day}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>`
                
                div.innerHTML = html;
                let card = div.firstChild;                
                container.appendChild(card);
    }
}