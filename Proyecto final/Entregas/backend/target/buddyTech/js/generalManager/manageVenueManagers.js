var subgerentesContainer = document.getElementById("subgerentesContainer");
const userN = document.getElementById("nameU");
const lastname = document.getElementById("lastname");
const id = document.getElementById("id");
const select = document.getElementById("select");
const filterbtn = document.getElementById("filter");
const cancelbtn = document.getElementById("cButton");
var venuesD;

const getCardInfo = async () => {

    let response1 = await fetch("http://localhost:8080/backend/api/users/cardInfo")
    let managerCards = await response1.json();
    await fillCards(managerCards);
    console.log("Rendering done")
}
const fillCards = async (managerCards) => {
    subgerentesContainer.innerHTML = "";
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

    let parameters = checkFilterP();
    let values = checkFilterV();

    console.log("http://localhost:8080/backend/api/users/managers"+parameters+"-"+values);
    let response = await fetch("http://localhost:8080/backend/api/users/managers"+parameters+"-"+values);
    let data = await response.json();
    await fillCards(data);
}


const checkFilterV = ()=>{

    let values = "";

    if(!userN.value==""){

        values += userN.value+","
    }

    if(!lastname.value==""){
        values += lastname.value+","
    }
    if(select.options[select.selectedIndex].text!="Seleccionar Sede"){
        let str =select.options[select.selectedIndex].text;
        let id = getVenueId(str);
        values += id+","
    }
    if(!id.value==""){
        values += id.value+",";
    }
    return values;
}

const checkFilterP = ()=>{
    let properties = "";

    if(!userN.value==""){
        properties+="name,"
    }
    if(!lastname.value==""){
        properties+="lastName,"
    }
    if(select.options[select.selectedIndex].text!="Seleccionar Sede"){
        properties+="venuesBuddyID,"
    }
    if(!id.value==""){

        properties+="id,"
    }

    return properties;

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

clearFields();
getVenues();
getCardInfo();