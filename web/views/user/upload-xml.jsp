<%-- 
    Document   : upload-xml
    Created on : 10/09/2018, 19:31:20
    Author     : Isabela
--%>

<style>
     
         .inputfile{
	width: 0.1px;
	height: 0.1px;
	opacity: 0;
	overflow: hidden;
	position: absolute;
	z-index: -1;
}
    </style>
    
<jsp:include page="../layout/header-auth.jsp" />

<h2 class="title-register">Upload de XML</h2>
<table id="upload" class="display table">
              
                
</table>
<form id="formulario" class="upload" method="post" enctype="multipart/form-data">
    <label>Importar arquivo <br><small> (Tipos de arquivo suportados: .xml)</small></label>
    <div class='card' style=" margin-top:  5px; padding-top: 3px;" >    
        <input type="file" name="file" id="file" class="inputfile"  accept=".xml" />
<label for="file" style="margin-left: 35px; width: 1000px;"> <i class="fa fa-folder-open" style="margin-right: 5px;"></i>Escolha um arquivo <button class="btn btn-danger btn-sm" style="width: 100px; margin-left:700px;">Enviar</button></label>
    

<div class="alert alert-info mt-2 mb-0" id="message">

    </div>
   
    </div>
    <input type="hidden" id="user" value="<%out.print(session.getAttribute("id"));%>">
    <input type="hidden" id="token" value="<%out.print(session.getAttribute("token"));%>">
    
    <!-- <button class="btn btn-secondary" type="button" > 
    <i class="fa fa-times ng-scope" aria-hidden="true"></i> Remover</button>-->
</form>
<div class="table-responsive">
    <table id="xml-list" class="display table">
        <thead>
            <tr>
                <th>#</th>
                <th>URL</th>
                <th>Data de envio</th>
            </tr>
        </thead>
        <tbody>
            <tr>

            </tr>
        </tbody>
    </table>
</div>
<jsp:include page="../layout/footer-auth.jsp"/>