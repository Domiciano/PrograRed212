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
        let iconsdiv = document.createElement("div");
        iconsdiv.classList.add("cardelement");
        let detaildiv = document.createElement("div");
        
        //detaildiv.classList.add("cardelement");
        detaildiv.classList.add("detaildiv");
        detaildiv.id= "details";
        

        iconsdiv.innerHTML = 
        '<i class="fas fa-user-circle dicon"></i>';

        id.innerHTML = '<label class="text label">Id:</label>'+'<label class="text">'+this.user.user.id+'</label>';
        nameV.innerHTML =  '<label class="name label">Nombre:</label>'+'<label class="name">'+this.user.user.name+'</label>';
        lastname.innerHTML = '<label class="text label">Apellido:</label>'+'<label class="text">'+this.user.user.lastName+'</label>';
        
        detaildiv.innerHTML= `<button id ="show" data-bs-placement="left" type="button" data-bs-toggle="popover" title="">Ver Detalles<i class="fas fa-chevron-right"></i></button>`; 

       // id.innerHTML = this.user.id;
        //nameV.innerHTML = this.user.name;
        //lastname.innerHTML = this.user.lastName;
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

       
 
        return component;
    }

    check=(user)=>{

        $('[data-bs-toggle="popover"]').popover({
            content:
            `<div id="detailContainer">
            
            <div class="row">
               <div class="col col1">
                    <h5>Id:</h5>
                    <p>`+user.user.id+`</p>
               </div>
               <div class="col col2">
                    <h5>Nombre:</h5>
                    <p>`+user.user.name+`</p>
               
                </div>
            </div>
           <div class="row">
               <div class="col col3">
                    <h5>Apellido:</h5>
                     <p>`+user.user.lastName+`</p>
               </div>
               <div class="col col4">
                    <h5>Sede:</h5>
                    <p>`+user.venueName+`</p>
                           
               </div> 
               
            <div class="row">
                <div class="col">
                   <a class="btn delete" id="btnDelete">Delete</a>
               </div>
           </div>
            `,
            html:true
            });

            $('[data-bs-toggle="popover"]').on('shown.bs.popover', function() {
                // set what happens when user clicks on the button
                $("#btnDelete").on('click', function(){
                    alert("clicked!!!");
                });
            });
            
           /* // when popover's content is hidden
            $('[data-bs-toggle="popover"]').on('hidden.bs.popover', function(){
                // clear listeners
                $("#btnDelete").off('click');
            });
          
             */

    }

}