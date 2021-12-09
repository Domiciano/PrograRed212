class staffView{

    
    //State
    constructor(user){
        this.user = user;
        
        //Object.seal(this);
    }


    
 
    render = ()=>{
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
        let roleBuddyID = document.createElement("div");
        roleBuddyID.classList.add("cardelement");
        let iconsdiv = document.createElement("div");
        iconsdiv.classList.add("cardelement");
        let detailbtn = document.createElement("a");
        detailbtn.classList.add("cardelement");
        let showDetail = document.createElement("div");
        iconsdiv.innerHTML = 
        '<i class="fas fa-user-circle dicon"></i>';
        detailbtn.innerHTML= 
        '<h4>Ver detalle</h4>'+
        '<i class="fas fa-chevron-right"></i>';
        detailbtn.classList.add("detail");
        detailbtn.id ="detail"
        detailbtn.setAttribute("data-toggle","popover");
        detailbtn.setAttribute("data-trigger","focus");
        id.innerHTML = '<label class="text label">Id:</label>'+'<label class="text">'+this.user.id+'</label>';
        nameV.innerHTML =  '<label class="name label">Nombre:</label>'+'<label class="name">'+this.user.name+'</label>';
        lastname.innerHTML = '<label class="text label">Apellido:</label>'+'<label class="text">'+this.user.lastName+'</label>';


         
       // id.innerHTML = this.user.id;
        //nameV.innerHTML = this.user.name;
        //lastname.innerHTML = this.user.lastName;
        venuesBuddyID.innerHTML = this.user.venuesBuddyID;
        roleBuddyID.innerHTML = this.user.roleBuddyID;

         //Agregar info tarjeta

        infodiv.appendChild(nameV);
        infodiv.appendChild(lastname);
        infodiv.appendChild(id);

                     //Agregar al componente principal 
        component.appendChild(iconsdiv);
        component.appendChild(infodiv);
        component.appendChild(detailbtn);
        
        component.classList.add("staffview"); //<div class="userview">  ....  </div>
    
 
        return component;
    }

}