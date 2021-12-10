var subgerentesContainer = document.getElementById("subgerentesContainer");
const userN = document.getElementById("nameU");
const lastname = document.getElementById("lastname");
const id = document.getElementById("id");
const select = document.getElementById("select");
const filterbtn = document.getElementById("filter");
const cancelbtn = document.getElementById("cButton");
var cardSelected = document.getElementById("cardSelected");
var venuesD;

const getCardInfo = async () => {

    let response = await fetch("http://localhost:8080/backend/api/users/null-null-null-null-2");
    let data = await response.json();
    await fillCards(data);
    console.log("Rendering done")
}
const fillCards = async (managerCards) => {
    subgerentesContainer.innerHTML = "";
    cardSelected.innerHTML = "";
    for(let i in managerCards) {
        let managerCard = managerCards[i];

        let cardView = new UserCard(managerCard);
        cardView.render(subgerentesContainer);
    }
}

const getVenues = async ()=>{
    let venuesNames = await fetch("http://localhost:8080/backend/api/venues/getvenues");
    let venues = await venuesNames.json();
    venuesD = venues;
    let html = `<option selected disabled selected hidden>Seleccionar Sede</option>`;
    for(let i in venues){
        let incomingName = venues[i].name;
        html += `<option value="${i}">${incomingName}</option>`;
    }
    select.innerHTML = html;

}


const getVenueId = (vname)=>{

    for(let i in venuesD){
        if(venuesD[i].name == vname){

            return venuesD[i].id;
        }
    }

}
const getUserByParam = async()=>{

    let filterdata = checkFilter();

    console.log("http://localhost:8080/backend/api/users/"+filterdata.idO+"-"+filterdata.nameO+"-"+filterdata.lastnameO+"-"+filterdata.venueO+"-2");
    let response = await fetch("http://localhost:8080/backend/api/users/"+filterdata.idO+"-"+filterdata.nameO+"-"+filterdata.lastnameO+"-"+filterdata.venueO+"-2");
    let data = await response.json();
    await fillCards(data);
}


const checkFilter = ()=>{

    let ln ="";
    let n = ""
    let vid = "";
    let nid = "";

    if(userN.value == ""){

        n = "null";

    }else{
        n = userN.value;
    }

    if(lastname.value ==""){

        ln = "null";
    }else{
        ln= lastname.value;
    }
    if(id.value == ""){

        nid = "null";
    }else{
        nid = id.value;
    }

    if(select.options[select.selectedIndex].text=="Seleccionar Sede"){
        vid = "null";
    }else{

        vid = getVenueId(select.options[select.selectedIndex].text);
    }

    let obj = {

        idO: nid,
        nameO : n,
        lastnameO: ln,
        venueO: vid
    };

    return obj
}
const clearFields = async()=>{
    userN.value = "";
    lastname.value = "";
    id.value = "";
    select.value = "Seleccionar Sede";


}

filterbtn.addEventListener("click", (event)=>{

    event.preventDefault();
    getUserByParam();
    clearFields();

});

cancelbtn.addEventListener("click",(event)=>{

    event.preventDefault();
    clearFields();
})

getVenues();
getCardInfo();