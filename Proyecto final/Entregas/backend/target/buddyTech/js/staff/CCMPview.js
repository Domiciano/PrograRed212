class CCMPview{

    constructor(CCMP){
      this.CCMP = CCMP;
      Object.seal(this);
    }

    render = (container) =>{
        let div = document.createElement("div");

                let html = `<div class="card mb-4 py-3 border-left-primary">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col">
                                        <i class="fas fa-user-circle"></i>
                                        </div>
                                        <div class="col">
                                            <h3 class="h3 mb-0 black font-weight-bold">${this.CCMP.client.name}</h3>
                                            <p class="mb-0">${this.CCMP.plan.name}</p>
                                            <p class="mb-0">${daysLeft(this.CCMP.membership.endDate)}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>`
        div.innerHTML = html;
        let card = div.firstChild;

        container.appendChild(card);
    }
}