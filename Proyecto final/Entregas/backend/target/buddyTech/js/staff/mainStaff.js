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

        } else {

                let clientFound = data[0];
                //Revisar que esté afuera
                if(clientFound.statusID === 3){

                    let validateMemberships = await fetch("http://localhost:8080/backend/api/ms/searchmembership/"+clientFound.membershipID);
                    let usermemship = await validateMemberships.json();
                    console.log(usermemship);
                    let memberEndDate;
                    for(let i in usermemship){
                        memberEndDate = usermemship[i].endDate;
                        actualMembership = usermemship[i];
                        console.log(actualMembership);      
                    }

                    let userEndDate = new Date(memberEndDate);
                    let userDate = userEndDate.getFullYear()+'-'+(userEndDate.getMonth()+1)+'-'+userEndDate.getDate();

                    let today = new Date();
                    let todayDate = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();

                    console.log("fecha usuario: "+userDate);
                    console.log("fecha actual: "+todayDate);
                    console.log(userDate > todayDate);

                    if(userDate > todayDate){
                        //Poner el cliente con el statusID de 2 ya que está adentro en ese momento (se hace despues de verificar el estado de la membresia)
                        let xhr = new XMLHttpRequest();
                        xhr.addEventListener('readystatechange', ()=>{
                            if(xhr.readyState == 4){
                                var response = JSON.parse(xhr.responseText);
                                console.log(response.message);
                                if(response.message == 'Estado del cliente ha sido cambiado'){

                                    //Cualquier cosa de observer
                                } else {
                                    alert('No se pudo cambiar el estado del cliente');
                                }
                            }
                        });
                    xhr.open('PUT', 'http://localhost:8080/backend/api/cls/editclientstatusbyid/'+clientFound.natId+'/'+2);
                    xhr.send();
                    }

                }  else if(clientFound.statusID === 2){
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