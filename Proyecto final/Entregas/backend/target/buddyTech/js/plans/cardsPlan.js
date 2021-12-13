class cardsPlan {
  constructor(plan) {
    this.plan = plan;
    Object.seal(this);
  }

  render = (container) => {
    let div = document.createElement("div");
    let html = "";

    html = `<div id="${this.plan.name}" class="card cardSelStyle border-left-success">
                          <div class="card-body">
                              <div class="row">
                                  <div class="column-1 centIcon">
                                      <div class="icons">
                                      <i class="fas fa-dollar-sign fa-3x"></i>
                                      </div>
                                  </div>
                                  <div class="column-2">
                                      <h3 class="h3 mb-0 ml-2 black font-weight-bold">${this.plan.name}</h3>
                                      <p class="mb-0 ml-2">Precio: ${this.plan.amount}</p>
                                      <p class="mb-0 ml-2">Duración: ${this.plan.time} días</p>
                                  </div>
                              </div>
                          </div>
                          <button class="seeDet">
                                        <details class="mb-1" data-popover="right">
                                            <summary>
                                                Edit
                                                <i class="fas fa-chevron-right pl-3"></i>
                                            </summary>

                                            <div class="box-shadow">
                                                <div class="row">
                                                    <div class="column">
                                                    <h5>Nombre</h5>
                                                    <input id="id" type="text" class="input" />
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="column">
                                                    <h5>Estado</h5>
                                                    <select class="selectC" id="selectStatusEdit">
                                                    <option selected disabled selected hidden>Seleccionar estado</option>
                                                    <option>Activo</option>
                                                    <option>Inactivo</option>
                                                    </select>
                                                    </div>
                                                </div>
                                                <div class="row mb-4">
                                                    <div class="column2">
                                                        <a class="btn deleteBtn mx-3">Delete</a>
                                                        <a class="btn editBtn mx-3">Edit</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </details>
                                    </button>
                      </div>
                      `;
    div.innerHTML = html;
    let cardComplete = div.firstChild;
    container.appendChild(cardComplete);

    var editBtn = cardComplete.querySelector(".editBtn");
    var editName = document.getElementById("id");
    var statusEdit = document.getElementById("selectStatusEdit");

    editBtn.addEventListener("click", async () => {
      let status = statusEdit.options[statusEdit.selectedIndex].text;
      if (status != "Seleccionar estado") {
        if (status == "Activo") {
          status = 1
        } else{
          status = 0;
        }
      } else{
        status = this.plan.active;
        if (status == true) {
          status = 1
        } else{
          status = 0;
        }
      }
      let planToUpdate = {
        id: this.plan.id,
        name: editName.value,
        amount: this.plan.amount,
        time: this.plan.time,
        active: status,
      };
      
      console.log(planToUpdate);

      let json = JSON.stringify(planToUpdate);

      let response = await fetch(
        "http://localhost:8080/backend/api/ps/update",
        {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: json,
        }
      );
      if (response.ok) {
        let data = await response.json();
        console.log(data);
        div.innerHTML = "";
        getCardInfo();
      }
      
    });
  };
}
