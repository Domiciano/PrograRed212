class staffView{

    
    //State
    constructor(user){
        this.user = user;
        
        //Object.seal(this);
    }


    
 
    render = ()=>{
        //Construir un elemento visual
        let component = document.createElement("div");      //<div></div>
        let id = document.createElement("div");
        id.classList.add("subtext");
        id.classList.add("text")
        let nameV = document.createElement("div");
        nameV.classList.add("name");          //<p></p>
        nameV.classList.add("text")
        let lastname = document.createElement("div");
        lastname.classList.add("subtext");
        lastname.classList.add("text")
        let venuesBuddyID = document.createElement("div")
        venuesBuddyID.classList.add("subtext");  
        venuesBuddyID.classList.add("text") 
        let roleBuddyID = document.createElement("div");
        roleBuddyID.classList.add("subtext");
        roleBuddyID.classList.add("text");
        let iconsdiv = document.createElement("div");
        let detailbtn = document.createElement("button");
        detailbtn.classList.add("detail");
        iconsdiv.classList.add("icons"); 
        let showDetail = document.createElement("div");
        iconsdiv.innerHTML = 
        '<i class="fas fa-arrow-circle-left hicon fas"></i>'+
        '<i class="fas fa-user-circle dicon"></i>';
        detailbtn.innerHTML= 
        '<h4>Ver detalle</h4>'+
        '<i class="fas fa-chevron-right"></i>';


        
         
        id.innerHTML = this.user.id;
        nameV.innerHTML = this.user.name;
        lastname.innerHTML = this.user.lastName;
        venuesBuddyID.innerHTML = this.user.venuesBuddyID;
        roleBuddyID.innerHTML = this.user.roleBuddyID;


        
        
        component.appendChild(lastname);
        component.appendChild(nameV); 
        component.appendChild(id);              //<div><p></p></div>  
        component.appendChild(iconsdiv);
        component.appendChild(detailbtn);
        
        component.classList.add("staffview"); //<div class="userview">  ....  </div>
    
 
        return component;
    }

}