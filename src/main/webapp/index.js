		/* active tabs selection starts here */
		
		document.querySelector(".taskSideButton").addEventListener("click",function(){
			document.getElementById("tasks").style.display = "flex";
			document.querySelector(".taskSideButton").classList.add("active")
			document.querySelector(".completedSideButton").classList.remove("active")
			document.getElementById("completed").style.display = "none"
		})

		document.querySelector(".completedSideButton").addEventListener("click",function(){
			document.getElementById("completed").style.display = "flex";
			document.querySelector(".completedSideButton").classList.add("active")
			document.querySelector(".taskSideButton").classList.remove("active")
			document.getElementById("tasks").style.display = "none"
		})
	
		/* active tabs selection ends here */





		/* edit icon button functionality starts here */
		let edits = document.getElementsByClassName("edit_popup");
		
		function editHandler(i){
			console.log(i)
			edits[i].style.display = "flex";
		}
		
		function editClose(i){
			console.log(i)
			edits[i].style.display = "none";
		}
		
		/* edit icon button functionality ends here */






		/* delete icon button functionality starts here */

		let deletesButton = document.getElementsByClassName("deleteTask_popup");
		
		function deleteHandler(i){
			console.log(i)
			deletesButton[i].style.display = "flex";
		}
		
		function deleteClose(i){
			console.log(i)
			deletesButton[i].style.display = "none";
		}

		/* delete icon button functionality ends here */





		/* task complete icon button functionality starts here */

		let taskCompleteButton = document.getElementsByClassName("taskComplete_popup");
		
		function taskCompleteHandler(i){
			taskCompleteButton[i].style.display = "flex";
		}
		
		/* task complete icon button functionality ends here */
		
		
		
		
		
		
		
		
		
		/* task regain icon button functionality starts here */

		let taskRegainButton = document.getElementsByClassName("taskRegain_popup");
		
		function taskRegainHandler(i){
			taskRegainButton[i].style.display = "flex";
		}
		
		/* task regain icon button functionality ends here */
		
		
		
		
		
		
		/* completeAllTask popup starts here */
		
		document.getElementById("completeAllTask").addEventListener("click",function(){
			document.querySelector(".completeAllTask_popup").style.display = "flex";
		})
		document.querySelector("#exit_popup").addEventListener("click",function(){
			document.querySelector(".completeAllTask_popup").style.display = "none";
		})
		
		/* completeAllTask popup ends here */
	
		
		
		
		
		
		
		/* deleteTask popup starts here */
		
		document.getElementById("deleteAllTask").addEventListener("click",function(){
			document.querySelector(".deleteAllTask_popup").style.display = "flex";
		})
		document.querySelector("#exit_popup").addEventListener("click",function(){
			document.querySelector(".deleteAllTask_popup").style.display = "none";
		})
		/* deleteTask popup ends here */
		
		
		
		
		
		
		/* completeAllTask popup starts here */
		
		document.getElementById("inCompleteAllTask").addEventListener("click",function(){
			document.querySelector(".incompleteTask_popup").style.display = "flex";
		})
		document.querySelector("#exit_popup").addEventListener("click",function(){
			document.querySelector(".incompleteTask_popup").style.display = "none";
		})
		
		/* completeAllTask popup ends here */