const planSelect = document.getElementById("planSelect");
const clientidTF = document.getElementById("clientidTF");
const citySelect = document.getElementById("citySelect");
const discountTF = document.getElementById("discountTF");
const cancelBtn = document.getElementById("cancelBtn");
const registerBtn = document.getElementById("registerBtn");
const myModal = new bootstrap.Modal(document.getElementById('staffCteClientModal'));
const modalBody = document.getElementById("staffCteClientModalBody");
var plansFull = undefined;
var venues = undefined;




const retrieveClient = () => {
    if(localStorage.getItem('clientToRenovate') !== null){
        let retrievedClient = localStorage.getItem('clientToRenovate');
        let parseClient = JSON.parse(retrievedClient);
        clientidTF.value = parseClient.natId;
    }
}

const getPlans = async () => {
    let html = `<option selected disabled selected hidden>Elegir Plan...</option>`;
    let plans = await fetch("http://localhost:8080/backend/api/ps/getactive");
    plansFull = await plans.json();
    console.log(plansFull);

    for (let i in plansFull) {
        let incomingName = plansFull[i].name;
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
retrieveClient();
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

    if ((validateSelectors()) && (clientidTF.value.lenght !== 0) && (discountTF.value.lenght !== 0)) {


        let searching = await fetch("http://localhost:8080/backend/api/cls/searchclient/"+clientidTF.value);
        let dataclient = await searching.json();
        console.log(dataclient);
        if (dataclient.lenght === 0) {

            let html = `<div class="row">
            <div class="column">
              <h5> Error </h5>
              <p class="mb-0"> El cliente con ID: ${clientidTF.value} no está registrado</p>
            </div>
            <div id="colerr" class="column">
              <h1 class="h1err">
              <i id="errModal" class="fas fa-exclamation-circle fa-5x"></i>
              </h1>
            </div>
          </div>`

            modalBody.innerHTML = html;
            myModal.show();
        } else {


            //Membership-------------------------------------------------     
            let myplan;
            for (let i in plansFull) {
                let incoming = plansFull[i];
                if (planSelect.options[planSelect.selectedIndex].text === plansFull[i].name) {
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

                dataclient[0].membershipID = memID;
                console.log("Cliente:");
                console.log(dataclient[0]);

                let jsonc = JSON.stringify(dataclient[0]);

                let clientresponse = await fetch("http://localhost:8080/backend/api/cls/editClient",
                    {
                        method: "PUT",
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
                  <h5> ${dataclient[0].name} ${dataclient[0].lastname}</h5>
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

const clearAll = () => {
    clientidTF.value = "";
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









