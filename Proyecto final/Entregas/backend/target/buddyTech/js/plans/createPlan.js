const nameTF = document.getElementById("nameTF");
const timeTF = document.getElementById("timeTF");
const statusSelector = document.getElementById("planActive");
const amountTF = document.getElementById("amountTF");
const cancelBtn = document.getElementById("cancelBtn");
const createBtn = document.getElementById("createBtn");
const myModal = new bootstrap.Modal(document.getElementById('plansCreatePlanModal'));
const modalBody = document.getElementById("plansCtePlanModalBody");


const validateSelector = () => {
    let validate = true;
    let selectedValuePlan = statusSelector.options[statusSelector.selectedIndex].text;

    if (selectedValuePlan == "Activo / Inactivo") {
        validate = false;
    }
    return validate;
}

const createPlan = async () => {
    modalBody.innerHTML = "";
    if ((nameTF.value.length !== 0) && (timeTF.value.length !== 0) && (validateSelector()) && (amountTF.value.length !== 0)) {
        let finalStatus = false;
        if(statusSelector.options[statusSelector.selectedIndex].text === "Activo"){
            finalStatus = true;
        }
        let plan = {
            id: 0,
            name: nameTF.value,
            time: timeTF.value,
            active: finalStatus,
            amount: amountTF.value
        };
        console.log(plan);
        let json = JSON.stringify(plan);
        let response = await fetch("http://localhost:8080/backend/api/ps/insert",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: json
            }
        );
        if (response.ok) {
            let publicado = "NO";
            if(finalStatus){
                publicado = "SI";
            }
            let html = `<div class="row">
            <div class="column">
              <h5> ${plan.name}</h5>
              <p class="mb-0"> Tiempo: ${plan.time} </p>
              <p class="mb-0"> Valor: ${plan.amount}</p>
              <p class="mb-0"> Activo: ${publicado}</p>
              <small> Plan creado correctamente </small>
            </div>
            <div id="colcheck" class="column">
              <h1 class="h1check">
                <i id="checkModal" class="fas fa-check fa-5x"></i>
              </h1>
            </div>
          </div>`

            modalBody.innerHTML = html;
            myModal.show();
        }
    } else {
        let html = `<div class="row">
        <div class="column">
          <h5> ATENCIÓN </h5>
          <p class="mb-0"> Porfavor complete todos los campos</p>
        </div>
        <div id="colwar" class="column">
          <h1 class="h1war">
            <i id="warningModal" class="fas fa-exclamation-triangle fa-5x"></i>
          </h1>
        </div>
      </div>`

       modalBody.innerHTML = html;    
       myModal.show();
    }
}

const clearFields = async () => {
    nameTF.value = "";
    timeTF.value = "";
    amountTF.value = "";
    statusSelector.value = "Activo / Inactivo";
}

createBtn.addEventListener("click", (event) => {
    event.preventDefault();
    createPlan();
    clearFields();
});

cancelBtn.addEventListener("click", (event) => {
    event.preventDefault();
    clearFields();
});