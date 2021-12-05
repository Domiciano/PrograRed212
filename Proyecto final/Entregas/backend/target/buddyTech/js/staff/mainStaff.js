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
        let searching = await fetch("http://localhost:8080/backend/api/cls/searchclient/"+userIdTF.value);
        let data = await searching.json();
        console.log(data);
        if(data.length === 0){
            alert("El usuario no está registrado jajaja pobre");

        } else{

                let clientFound = data[0];
                //Revisar que esté afuera
                if(clientFound.statusID === 3){

                    let validateMemberships = await fetch("http://localhost:8080/backend/api/ms/searchmembership/"+clientFound.id);
                    let memberships = await validateMemberships.json();
                    console.log(memberships);
                    //Poner el cliente con el statusID de 2 ya que está adentro en ese momento (se hace despues de verificar el estado de la membresia)
                    
                } else if(clientFound.statusID === 2){
                    alert("La membresia del cliente ya está en uso");

                } else if(clientFound.statusID === 1){
                    alert("La persona se encuentra bloqueada en su totalidad");

                }
            
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