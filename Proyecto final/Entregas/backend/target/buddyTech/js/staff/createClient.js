const nameTF = document.getElementById("nameTF");
const lastNameTF = document.getElementById("lastNameTF");
const planSelect = document.getElementById("planSelect");
const clientidTF = document.getElementById("clientidTF");
const ageTF = document.getElementById("ageTF");
const citySelect = document.getElementById("citySelect");
const heightTF = document.getElementById("heightTF");
const weightTF = document.getElementById("weightTF");



const getVenues = async ()=>{

    let plansNames = await fetch("http://localhost:8080/backend/api/ps/getactive");
    let names = await plansNames.json();
    console.log(names);
}

getVenues();



const getPlans = async ()=>{



}



const create = async ()=>{




}