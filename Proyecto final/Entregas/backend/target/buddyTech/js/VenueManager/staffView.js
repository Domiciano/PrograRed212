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
        let id = document.createElement("label");
        id.classList.add("subtext");
        id.classList.add("text")
        let nameV = document.createElement("label");
        nameV.classList.add("name");          //<p></p>
        nameV.classList.add("text")
        let lastname = document.createElement("label");
        lastname.classList.add("subtext");
        lastname.classList.add("text")
        let venuesBuddyID = document.createElement("label")
        venuesBuddyID.classList.add("subtext");  
        venuesBuddyID.classList.add("text") 
        let roleBuddyID = document.createElement("label");
        roleBuddyID.classList.add("subtext");
        roleBuddyID.classList.add("text");
        let iconsdiv = document.createElement("div");
        let detailbtn = document.createElement("button");
        detailbtn.classList.add("detail");
        iconsdiv.classList.add("icons"); 
        let showDetail = document.createElement("div");
        iconsdiv.innerHTML = 
        '<i class="fas fa-user-circle dicon"></i>';
        detailbtn.innerHTML= 
        '<h4>Ver detalle</h4>'+
        '<i class="fas fa-chevron-right"></i>';

        
        
         
        id.innerHTML = this.user.id;
        nameV.innerHTML = this.user.name;
        lastname.innerHTML = this.user.lastName;
        venuesBuddyID.innerHTML = this.user.venuesBuddyID;
        roleBuddyID.innerHTML = this.user.roleBuddyID;

        infodiv.appendChild(nameV);
        infodiv.appendChild(lastname);
        infodiv.appendChild(id);

        component.appendChild(iconsdiv);
        component.appendChild(infodiv);              //<div><p></p></div>  
        component.appendChild(detailbtn);
        
        component.classList.add("staffview"); //<div class="userview">  ....  </div>
    
 
        return component;
    }

}