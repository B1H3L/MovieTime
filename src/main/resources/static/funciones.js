function eliminar(codigoE){
	swal({
  title: "Estas seguro de eliminar?",
  text: "¡Una vez eliminado, no podrás recuperarlo!",
  icon: "warning",
  buttons: true,
  dangerMode: true,
})
.then((OK) => {
  if (OK) {
	  $.ajax({
		  url:"/eliminar/"+codigoE,succes : function(res){
			  console.log(res);
		  }
	  });
    swal("¡Supervisor eliminado correctamente!", {
      icon: "success",
    }).then((ok)=>
    {
		if(ok){
			location.href="/listarEmpleado";
		}
	});
  } else {
    swal("Eliminacion cancelada!");
  }
});
	
	
	
	
}