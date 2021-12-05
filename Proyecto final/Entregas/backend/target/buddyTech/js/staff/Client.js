class Client{

    constructor(client){
      this.client = client;
      Object.seal(this);
    }

    render = (container) =>{
        let div = document.createElement("div");

                let html = `<div class="card mb-4 py-3 border-left-primary">
                                <div class="card-body">
                                    
                                </div>
                            </div>`
        div.innerHTML = html;
        let card = div.firstChild;

        if(this.user.blocked){
            card.classList.add("text-white")
            card.classList.add("bg-secondary")
        }

        container.appendChild(card);

        let blockBtn = document.getElementById(this.user.id);
        blockBtn.addEventListener("click", (e)=>{
            e.preventDefault();
            alert(this.user.name)
        });
    }
}