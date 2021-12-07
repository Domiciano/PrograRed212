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
        let name = document.createElement("div");          //<p></p>
        let lastname = document.createElement("div");
        let venuesBuddyID = document.createElement("div");
        let roleBuddyID = document.createElement("div");


        //Agregar clases para los css

        name.classList.add("name");
        lastname.classList.add("subtext");
        id.classList.add("subtext");
        venuesBuddyID.classList.add("subtext");
        roleBuddyID.classList.add("subtext");

 
                      //<p></p>

       /* this.prev =()=>{
            if(this.task.statusId ==4){

                var newid = this.task.statusId-2;
            }else{

                var newid = this.task.statusId-1;
            }
            
            let task = {

                id: this.task.id,
                statusId: newid
            };

             updateTask(task);

        }
        
        this.next =()=>{
            if(this.task.statusId ==2){

                var newid = this.task.statusId+2;
            }else{

                var newid = this.task.statusId+1;
            }
            
            let task = {

                id: this.task.id,
                statusId: newid
            };

             updateTask(task);

        }
        
        this.delete =()=>{
           
            let task = {

                id: this.task.id,
            };

             deleteTask(task);

        }

        if(this.task.statusId == 1 || this.task.statusId == 2 ){
            
            let nextBtn = document.createElement("button");
             nextBtn.classList.add("button2");
             nextBtn.addEventListener("click",(event)=>{
                event.preventDefault();
                this.next();
                
            }); 
             component.appendChild(nextBtn);
        

        }
         if(this.task.statusId == 2 || this.task.statusId == 4){
            let prevBtn = document.createElement("button");
            prevBtn.classList.add("button3");
            prevBtn.addEventListener("click",(event)=>{ 
                event.preventDefault();
                this.prev();
                
            });
            component.appendChild(prevBtn);
        } */

      
        id.innerHTML = this.user.id;
        name.innerHTML = this.user.name;
        lastname.innerHTML = this.user.lastName;
        venuesBuddyID.innerHTML = this.user.venuesBuddyID;
        roleBuddyID.innerHTML = this.user.roleBuddyID;
        
        component.appendChild(name); 
        component.appendChild(lastname)
        component.appendChild(id);              //<div><p></p></div>  
        component.appendChild(roleBuddyID);  
        component.appendChild(venuesBuddyID);
        
        component.classList.add("staffview"); //<div class="userview">  ....  </div>
    
 
        return component;
    }

    
}