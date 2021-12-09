const nameTF = document.getElementById("nameTF");
const lastNameTF = document.getElementById("lastNameTF");
const planSelect = document.getElementById("planSelect");
const clientidTF = document.getElementById("clientidTF");
const ageTF = document.getElementById("ageTF");
const citySelect = document.getElementById("citySelect");
const heightTF = document.getElementById("heightTF");
const weightTF = document.getElementById("weightTF");




const getPlans = async ()=>{

    let plansNames = await fetch("http://localhost:8080/backend/api/ps/getactive");
    let names = await plansNames.json();
    console.log(names);
    let html = `<option selected disabled selected hidden>Elegir Plan...</option>`;
    
    for(let i in names){
        let incomingName = names[i];
            html += `<option value="${i}">${incomingName}</option>`;        
    }
    planSelect.innerHTML = html;
}

const getVenues = async ()=>{
    let venuesNames = await fetch("http://localhost:8080/backend/api/venues/getvenues");
    let venues = await venuesNames.json();
    console.log(venues);
    let html = `<option selected disabled selected hidden>Seleccionar Ciudad...</option>`;
    for(let i in venues){
        let incomingName = venues[i].name;
            html += `<option value="${i}">${incomingName}</option>`;        
    }
    citySelect.innerHTML = html;
}

getPlans();
getVenues();



const create = async ()=>{




}