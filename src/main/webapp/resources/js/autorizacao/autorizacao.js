$(document).ready(function(){
	
	$("#btn-add-exame").click(function(){
		$("#formularioAutorizacao").attr('action', $("#url-add").val());
		$("#formularioAutorizacao").submit();
	});
	
	$("#btn-gerar").click(function(){
		$("#formularioAutorizacao").attr('action', $("#url-gerar").val());
		$("#formularioAutorizacao").submit();
	});
	
	$("#btn-cancelar").click(function(){
		$("#formularioAutorizacaoDetalhar").attr('action', $("#url-cancelar").val());
		$("#formularioAutorizacaoDetalhar").submit();
	});
	
	$("#btn-emitir").click(function(){
		$("#formularioAutorizacaoDetalhar").attr('action', $("#url-emitir").val());
		$("#formularioAutorizacaoDetalhar").submit();
	});
	
	$("#btn-autorizar").click(function(){
		$("#formularioAutorizacaoDetalhar").attr('action', $("#url-autorizar").val());
		$("#formularioAutorizacaoDetalhar").submit();
	});
	
	$('#selectHospital').change(function() {
		var codigoHospital = $(this).val();
		$("#inputValorSala").val(null);
        $.ajax({ 
            type: "GET",   
            url: "buscar-hospital/" + codigoHospital,
            success:   function(response){ 
            		$("#inputValorSala").val(response.valorTransiente);
		         },
		         error: function(e){
		         }
        });  
	});
	
	$('#selectFornecedor').change(function() {
		var codigoFornecedor = $(this).val();
		$("#inputValorMaterial").val(null);
        $.ajax({ 
            type: "GET",   
            url: "buscar-fornecedor/" + codigoFornecedor,
            success:   function(response){ 
            		$("#inputValorMaterial").val(response.valorTransiente);
		         },
		         error: function(e){
		         }
        });  
	});
	
	$('#selectCredenciado').change(function() {
		var codigoCredenciado = $(this).val();
		$("#inputValorCredenciado").val(null);
        $.ajax({ 
            type: "GET",   
            url: "buscar-credenciado/" + codigoCredenciado,
            success:   function(response){ 
            		$("#inputValorCredenciado").val(response.valorCobradoTransiente);
		         },
		         error: function(e){
		         }
        });  
	});
	
	$("#divHospital").hide();
	$("#divFornecedor").hide();
	$("#divExame").hide();
	$("#divTabelaExames").hide();
	$("#divBeneficiario").hide();
	$("#inputDataAutorizacao").mask("99/99/9999");
	$('.js-decimal').maskMoney({decimal:',', thousands:'.'});
	$("#divValorCredenciado").show();
	
	$("#tipoAutorizacao").change(function() {		
		if ($(this).find('option:selected').val() === '1') {
			$("#divBeneficiario").show();
			$("#divHospital").hide();
			$("#divFornecedor").hide();
			$("#divExame").hide();
			$("#divTabelaExames").hide();
			$("#divValorCredenciado").show();
		} else if ($(this).find('option:selected').val() === '2') {
			$("#divHospital").show();
			$("#divFornecedor").hide();
			$("#divExame").show();
			$("#divTabelaExames").show();
			$("#divBeneficiario").show();
			$("#divValorCredenciado").hide();
			$("#btn-add-exame").show();
		} else if ($(this).find('option:selected').val() === '3') {
			$("#divHospital").show();
			$("#divFornecedor").show();
			$("#divTabelaExames").hide();
			$("#divBeneficiario").show();
			$("#divExame").show();
			$("#btn-add-exame").hide();
			$("#divValorCredenciado").hide();
		} else {
			$("#divHospital").hide();
			$("#divFornecedor").hide();
			$("#divTabelaExames").hide();
			$("#divBeneficiario").hide();
			$("#divExame").hide();
			$("#divValorCredenciado").show();
		}				
	});
	
	if ($("#tipoAutorizacao").val() === '1') {
		$("#divBeneficiario").show();
		$("#divHospital").hide();
		$("#divFornecedor").hide();
		$("#divTabelaExames").hide();
		$("#divExame").hide();
		$("#divValorCredenciado").show();
	}
	
	if ($("#tipoAutorizacao").val() === '2') {
		$("#divHospital").show();
		$("#divFornecedor").hide();
		$("#divTabelaExames").show();
		$("#divBeneficiario").show();
		$("#divExame").show();
		$("#divValorCredenciado").hide();
		$("#btn-add-exame").show();
	}
	
	if ($("#tipoAutorizacao").val() === '3') {
		$("#divHospital").show();
		$("#divFornecedor").show();
		$("#divTabelaExames").hide();
		$("#divBeneficiario").show();
		$("#divExame").show();
		$("#divValorCredenciado").hide();
		$("#btn-add-exame").hide();
	}
	
})