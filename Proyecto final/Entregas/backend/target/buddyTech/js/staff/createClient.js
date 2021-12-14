const nameTF = document.getElementById("nameTF");
const lastNameTF = document.getElementById("lastNameTF");
const planSelect = document.getElementById("planSelect");
const clientidTF = document.getElementById("clientidTF");
const ageTF = document.getElementById("ageTF");
const citySelect = document.getElementById("citySelect");
const heightTF = document.getElementById("heightTF");
const weightTF = document.getElementById("weightTF");
const cancelBtn = document.getElementById("cancelBtn");
const registerBtn = document.getElementById("registerBtn");
const discountTF = document.getElementById("discountTF");
const myModal = new bootstrap.Modal(document.getElementById('staffCteClientModal'));
const modalBody = document.getElementById("staffCteClientModalBody");
var statusReady = undefined;
var venues = undefined;
/*
let html1 = `<option selected disabled selected hidden>Elegir Plan...</option>`;
html1 += `<option value="1">Bronce</option>`;
planSelect.innerHTML = html1;

let html2 = `<option selected disabled selected hidden>Seleccionar Ciudad...</option>`;
html2 += `<option value="1">Sede Cali</option>`;
citySelect.innerHTML = html2;
*/
const getPlans = async () => {
    let html = `<option selected disabled selected hidden>Elegir Plan...</option>`;
    let plans = await fetch("http://localhost:8080/backend/api/ps/getactive");
    statusReady = await plans.json();
    console.log(statusReady);

    for (let i in statusReady) {
        let incomingName = statusReady[i].name;
        html += `<option value="${i}">${incomingName}</option>`;
    }
    planSelect.innerHTML = html;
}

const getVenues = async () => {

    let html = `<option selected disabled selected hidden>Seleccionar Ciudad...</option>`;
    let venuesNames = await fetch("http://localhost:8080/backend/api/venues/getvenues");
    venues = await venuesNames.json();
    console.log(venues);
    for (let i in venues) {
        let incomingName = venues[i].name;
        html += `<option value="${i}">${incomingName}</option>`;
    }
    citySelect.innerHTML = html;
}

getPlans();
getVenues();

const validateSelectors = () => {
    let validate = true;
        let selectedValuePlan = planSelect.options[planSelect.selectedIndex].text;
        let selectedValueCity = citySelect.options[citySelect.selectedIndex].text;
        
        if ((selectedValuePlan == "Elegir Plan...") || (selectedValueCity == "Seleccionar Ciudad...")) {
            validate = false;
        }
    

    return validate;
}

const createClient = async () => {
    modalBody.innerHTML = "";
    if ((validateSelectors()) && (nameTF.value.lenght !== 0) && (lastNameTF.value.lenght !== 0) && (clientidTF.value.lenght !== 0) &&
        (ageTF.value.lenght !== 0) && (heightTF.value.lenght !== 0) && (weightTF.value.lenght !== 0) && (discountTF.value.lenght !== 0)) {


        //Membership-------------------------------------------------     
        let myplan;
        for (let i in statusReady) {
            let incoming = statusReady[i];
            if (planSelect.options[planSelect.selectedIndex].text === statusReady[i].name) {
                myplan = incoming;
                break;
            }
        }
        let myvenue;
        for (let i in venues) {
            let incomven = venues[i];
            if (citySelect.options[citySelect.selectedIndex].text === venues[i].name) {
                myvenue = incomven;
                break;
            }
        }
        console.log(myplan);

        let memship = {
            id: 0,
            totalAmount: myplan.amount,
            discount: discountTF.value,
            days: myplan.time,
            planID: myplan.id,
            venueID: myvenue.id

        };
        console.log(memship);

        let jsonm = JSON.stringify(memship);

        let memresponse = await fetch("http://localhost:8080/backend/api/ms/addmembershipnew",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: jsonm
            }
        );
        if (memresponse.ok) {
            let thismem = await memresponse.json();
            console.log("imprimiendo membresia");
            console.log(thismem);
            //Cambio el botón
            //submitBtn.innerHTML = postOk;

            //Cliente -------------------------------------------------
            let memID = thismem[0].id;
            let client = {

                natId: clientidTF.value,
                id: 0,
                age: ageTF.value,
                name: nameTF.value,
                lastname: lastNameTF.value,
                weight: weightTF.value,
                height: heightTF.value,
                statusID: 3,
                membershipID:memID

            };
            console.log("Cliente:");
            console.log(client);

            let jsonc = JSON.stringify(client);

            let clientresponse = await fetch("http://localhost:8080/backend/api/cls/addClient",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: jsonc
                }
            );
            if (clientresponse.ok) {
                let data = await clientresponse.json();
                console.log(data);
                //Cambio el botón
                //submitBtn.innerHTML = postOk;

                
                let html = `<div class="row">
                <div class="column">
                  <h5> ${client.name} ${client.lastname}</h5>
                  <p class="mb-0"> ${myplan.name} </p>
                  <p class="mb-0"> Fecha de inicio: ${thismem[0].startDate}</p>
                  <p class="mb-0"> Fecha de finalización: ${thismem[0].endDate}</p>
                  <small> Success</small>
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

const clearAll = () =>{
    nameTF.value = "";
    lastNameTF.value = "";
    clientidTF.value = "";
    ageTF.value = "";
    citySelect.value = "";
    heightTF.value = "";
    weightTF.value = "";
    discountTF.value = "";
}


registerBtn.addEventListener("click", (event) => {
    event.preventDefault();
    createClient();
});

cancelBtn.addEventListener("click", (event) => {
    event.preventDefault();
    clearAll();
});









