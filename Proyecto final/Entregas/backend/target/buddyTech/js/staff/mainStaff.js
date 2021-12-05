const loginBtn = document.getElementById("loginBtn");
const userIdTF = document.getElementById("userIdTF");
const oneBtn = document.getElementById("oneBtn");
const twoBtn = document.getElementById("twoBtn"); 
const threeBtn = document.getElementById("threeBtn");
const fourBtn = document.getElementById("fourBtn");
const fiveBtn = document.getElementById("fiveBtn");
const sixBtn = document.getElementById("sixBtn");
const sevenBtn = document.getElementById("sevenBtn");
const eightBtn = document.getElementById("eightBtn");
const nineBtn = document.getElementById("nineBtn");
const zeroBtn = document.getElementById("zeroBtn");
const clearBtn = document.getElementById("clearBtn");


const login = async ()=>{

    //Al darle login que me mande a tal página, se debe guardar
    //el cliente en localstorage
    if(userIdTF.value.length !== 0){
        let responseToDo = await fetch("http://localhost:8080/backend/api/cls/searchclient/"+userIdTF.value);
        let data = await responseToDo.json();
        console.log(data);
        if(data.length === 0){
            alert("El usuario no está registrado jajaja pobre");
        }
    
    
       // location.href = "dashboard.html";
    } else {
        alert("Ingrese un documento válido");
    }

};

loginBtn.addEventListener("click", (event) =>{
    event.preventDefault();
    login();
});