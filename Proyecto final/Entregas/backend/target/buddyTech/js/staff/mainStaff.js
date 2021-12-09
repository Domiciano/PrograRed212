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
const myModal = new bootstrap.Modal(document.getElementById('loginModal'));
const modalBody = document.getElementById("loginModalBody");


const login = async ()=>{
    modalBody.innerHTML = "";
    //Al darle login que me mande a tal página, se debe guardar
    //el cliente en localstorage
    if(userIdTF.value.length !== 0){
        let searching = await fetch("http://localhost:8080/backend/api/cls/cardInfo/"+userIdTF.value); 

        let data = await searching.json();
        console.log(data);
        if(data.length === 0){

          let html = `<div class="row">
          <div class="column">
            <h5> Error </h5>
            <p class="mb-0"> El cliente con ID: ${userIdTF.value} no está registrado</p>
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

                let clientFound = data[0];
                //Revisar que esté afuera
                if(clientFound.client.statusID === 3){
                    /*
                    let validateMemberships = await fetch("http://localhost:8080/backend/api/ms/searchmembership/"+clientFound.membershipID);
                    let usermemship = await validateMemberships.json();
                    console.log(usermemship);
                    */
                    let memberEndDate;
                    //for(let i in usermemship){
                        memberEndDate = clientFound.memEndDate;
                        //actualMembership = usermemship[i];    
                    //}
                    
                    console.log(evaluateDateAccess(memberEndDate));
                    if(evaluateDateAccess(memberEndDate)){
                        //Poner el cliente con el statusID de 2

                        let xhr = new XMLHttpRequest();
                        xhr.addEventListener('readystatechange', ()=>{
                            if(xhr.readyState == 4){
                                var response = JSON.parse(xhr.responseText);
                                console.log(response.message);
                                if(response.message == 'Estado del cliente ha sido cambiado'){
                                    let days = daysLeft(memberEndDate);
                    
                                    let html = `<div class="row">
                                    <div class="column">
                                      <h5> ${clientFound.client.name} ${clientFound.client.lastname}</h5>
                                      <p class="mb-0"> Tipo de plan: ${clientFound.planName}</p>
                                      <p class="mb-0"> Dias restantes: ${days}</p>
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
                                } else {
            
                                  let html = `<div class="row">
                                  <div class="column">
                                    <h5> ${clientFound.client.name} ${clientFound.client.lastname} </h5>
                                    <p class="mb-0">No se pudo cambiar el estado de ingreso en la base de datos</p>
                                  </div>
                                  <div id="colerr" class="column">
                                    <h1 class="h1err">
                                    <i id="errModal" class="fas fa-exclamation-circle fa-5x"></i>
                                    </h1>
                                  </div>
                                </div>`
           
                                 modalBody.innerHTML = html;    
                                 myModal.show();
                                }
                            }
                        });
                    xhr.open('PUT', 'http://localhost:8080/backend/api/cls/editclientstatusbyid/'+clientFound.client.natId+'/'+2);
                    xhr.send();
                    } else {
                        //alert("La membresía ha expirado, porfavor contacte a un staff para renovarla");
                       let days = daysLeft(memberEndDate);
                       let html = `<div class="row">
                       <div class="column">
                         <h5> ${clientFound.client.name} ${clientFound.client.lastname} </h5>
                         <p class="mb-0"> Tipo de plan: ${clientFound.planName}</p>
                         <p class="mb-0"> Dias restantes: ${days}</p>
                         <small>La membresía ha expirado, porfavor contacte a un staff para renovarla</small>
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

                }  else if(clientFound.client.statusID === 2){

                  let html = `<div class="row">
                  <div class="column">
                    <h5> ${clientFound.client.name} ${clientFound.client.lastname} </h5>
                    <p class="mb-0">Membresía en uso acutalmente, no puede ingresar con la misma identidad</p>
                  </div>
                  <div id="colerr" class="column">
                    <h1 class="h1err">
                    <i id="errModal" class="fas fa-exclamation-circle fa-5x"></i>
                    </h1>
                  </div>
                </div>`

                 modalBody.innerHTML = html;    
                 myModal.show();

                } else if(clientFound.client.statusID === 1){
                    //La persona se encuentra bloqueada en su totalidad

                    let html = `<div class="row">
                    <div class="column">
                      <h5> ${clientFound.client.name} ${clientFound.client.lastname} </h5>
                      <p class="mb-0"> Cliente bloqueado permanentemente</p>
                    </div>
                    <div id="colerr" class="column">
                      <h1 class="h1err">
                      <i id="errModal" class="fas fa-exclamation-circle fa-5x"></i>
                      </h1>
                    </div>
                  </div>`

                  modalBody.innerHTML = html;        
                  myModal.show();
                }     
            }
       // location.href = "dashboard.html";
    } else {
      let html = `<div class="row">
      <div class="column">
        <h5> Error </h5>
        <p class="mb-0">Ingrese un documento válido</p>
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

};

loginBtn.addEventListener("click", (event) =>{
    event.preventDefault();
    login();
});

zeroBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "0";
}); 
oneBtn.addEventListener("click", (event) =>{
event.preventDefault();
userIdTF.value += "1";
});
twoBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "2"; 
  });
threeBtn.addEventListener("click", (event) =>{
    event.preventDefault();
    userIdTF.value += "3";   
});
fourBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "4";
});
fiveBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "5";
});
sixBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "6";
});
sevenBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "7";
});
eightBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "8";
});
nineBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value += "9";
});   
clearBtn.addEventListener("click", (event) =>{
  event.preventDefault();
  userIdTF.value = "";
}); 
  



