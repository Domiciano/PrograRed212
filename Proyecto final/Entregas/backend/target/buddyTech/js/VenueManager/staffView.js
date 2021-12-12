class staffView{

    
    //State
    constructor(user){
        this.user = user;   
        this.show = null
        this.delete = null
        //Object.seal(this);
    }

 
    render = (container)=>{

        //Construir un elemento visual
        let component = document.createElement("div");
              //<div></div>
        let infodiv = document.createElement("div");
        infodiv.classList.add("info");
        let id = document.createElement("div");
        id.classList.add("cardelement");
        let nameV = document.createElement("div");
        nameV.classList.add("cardelement");          //<p></p>
        let lastname = document.createElement("div");
        lastname.classList.add("cardelement");
        let venuesBuddyID = document.createElement("div")
        venuesBuddyID.classList.add("cardelement");  
        let iconsdiv = document.createElement("div");
        iconsdiv.classList.add("cardelement");
         let detaildiv = document.createElement("div");
          //let detaildiv= document.getElementById("detaildiv");
        
      detaildiv.classList.add("cardelement");
      detaildiv.classList.add("detaildiv");
    //    detaildiv.id= "details";
        

        iconsdiv.innerHTML = 
        '<i class="fas fa-user-circle dicon"></i>';

        id.innerHTML = '<label class="text label">Id:</label>'+'<label class="text">'+this.user.user.id+'</label>';
        nameV.innerHTML =  '<label class="name label">Nombre:</label>'+'<label class="name">'+this.user.user.name+'</label>';
        lastname.innerHTML = '<label class="text label">Apellido:</label>'+'<label class="text">'+this.user.user.lastName+'</label>';
        
       detaildiv.innerHTML= `<button class="seeDet">
        <details class="mb-1" data-popover="left">
            <summary>
                See detail
                <i class="fas fa-chevron-right pl-3"></i>
            </summary>

            <div class="box-shadow">
                <div class="row">
                    <div class="column">
                        <h5>name</h5>
                        <p>Juan</p>
                    </div>
                    <div class="column">
                        <h5>lastname</h5>
                        <p>Gómez</p>
                    </div>
                </div>
                <div class="row">
                    <div class="column">
                        <h5>natid</h5>
                        <p>3210684</p>
                    </div>
                    <div class="column">
                        <h5>plan</h5>
                        <p>Gold</p>
                    </div>
                </div>
                <div class="row">
                    <div class="column">
                        <h5>height</h5>
                        <p>1.80</p>
                    </div>
                    <div class="column">
                        <h5>weight</h5>
                        <p>60kg</p>
                    </div>
                </div>
                <div class="row">
                    <div class="column2">
                        <a id="delete${this.user.user.id}btn"class="btn detailDelete">Delete</a>
                    </div>
                </div>
            </div>
        </details>
    </button>`; 
   
          
        venuesBuddyID.innerHTML = this.user.venueName;

         //Agregar info tarjeta

        infodiv.appendChild(nameV);
        infodiv.appendChild(lastname);
        infodiv.appendChild(id);

                     //Agregar al componente principal 
        component.appendChild(iconsdiv);
        component.appendChild(infodiv);
        component.appendChild(detaildiv);

        
        component.classList.add("staffview"); //<div class="userview">  ....  </div>

        container.appendChild(component);
        let deletebtn = document.getElementById("delete"+this.user.user.id+"btn");  
        deletebtn.addEventListener("click",(event)=>{
           
            event.preventDefault();
            deleteStaff(this.user.user.id+"");
        })
         
       
 
        
    }

}