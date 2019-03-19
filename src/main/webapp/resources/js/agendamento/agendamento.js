$(document).ready(function() {

	/*YUI({
		lang : "pt-BR"
	}).use(
			'aui-scheduler',
			function(Y) {

			var eventRecorder = new Y.SchedulerEventRecorder({

					 on : {
						save : function(event) {
							console.log(event)
							alert('Evento:' + this.getContentNode().val() + ' '
									+ '\nendDate: '
									+ eventRecorder.get('endDate'));
						}
					} 
				});  

				var weekView = new Y.SchedulerWeekView({
					isoTime : true,
					strings : es_ES_strings_allDay
				});

				var dayView = new Y.SchedulerDayView({
					isoTime : true,
					strings : es_ES_strings_allDay
				});

				var monthView = new Y.SchedulerMonthView({
					isoTime : true,
					strings : {
						showMore : 'mostrar {0} mais',
						close : 'fechar'
					}
				});

				var es_ES_strings_allDay = {
					allDay : 'todo o dia'
				};

				new Y.Scheduler({
					boundingBox : '#myScheduler',
					date : new Date(),
					//eventRecorder : eventRecorder,
					render : true,
					// https://alloyui.com/api/classes/A.Scheduler.html#attr_strings
					// https://github.com/liferay/alloy-ui/blob/3.0.3-deprecated.65/src/aui-scheduler/js/aui-scheduler-base.js#L606-L622
					strings : {
						agenda : 'agenda',
						day : 'dia',
						month : 'mês',
						today : 'hoje',
						week : 'semana',
						year : 'ano'
					},
					views : [ weekView, dayView, monthView ],
					// https://alloyui.com/api/classes/A.SchedulerEventRecorder.html#attr_strings
					// https://github.com/liferay/alloy-ui/blob/3.0.3-deprecated.65/src/aui-scheduler/js/aui-scheduler-view-week.js#L19
					eventRecorder : new Y.SchedulerEventRecorder({
						strings : {
							'delete' : 'apagar',
							'description-hint' : '',
							cancel : 'cancelar',
							description : 'descrição',
							edit : 'editar',
							save : 'salvar',
							when : 'quando'
						},
						on : {
							save : function(event) {
								console.log(event)
								alert('Evento:' + this.getContentNode().val()
										+ ' ' + '\nendDate: '
										+ eventRecorder.get('endDate')
										+ eventRecorder.get('starDate'));

								$.ajax({
									type : "POST",
									url : "evento/salvar",
									data : JSON.stringify({agendamentoDTO : {
										'nomeEvento' : this.getContentNode().val(),
										'dataConsultaFormatada' : eventRecorder.get('startDate'),
										'horaConsultaFormatada' : eventRecorder.get('endDate'),
										'medico.id' : $('#id').val()
									}})							,
									contentType: "application/json; charset=utf-8",
									dataType : "json",
									success:function(res){
										console.log(res)
									},
									error:function(err){
										console.log(err)
									}
								});

								 $.post('evento/salvar', {
									'nome' : this.getContentNode().val()
								}).done(function(d) {

								}).fail(function(err) {

								}); 

								
							}
						}
					})
				});

			});*/

	$("#inputDataConsulta").mask("99/99/9999");
	$("#inputDataNascimentoNaoCadastrado").mask("99/99/9999");
	$("#hora").mask("99:99:99");
	$("#inputCelular").mask("(99)99999-9999");
	$("#inputDataNascimento").mask("99/99/9999");
	$("#inputDataNascimento2").mask("99/99/9999");
	$("#inputTelefone").mask("(99)9999-9999");

	$(".cpf").mask("999.999.999-99");
	aplicarMascaraExame($("#inputValorConsulta"));

	$("#a-selecionar-paciente").click(function() {

		var idPoste = $("input[name='idPaciente']:checked").val();
		if (idPoste != null) {
			$("#id-paciente").attr("value", idPoste);
		}

		$("#form-selecionar").submit();
	});
	
	// Procura os setores por empresa.
	/*$('#inputMedico').change(function() {
				var idMedico = $(this).val();
	            $.ajax({ 
	                type: "GET",   
	                url: "evento/buscarMedico/" + idMedico,
	                success:   function(response){
	                	 
	   		         },
	   		         error: function(e){
	   		         }
	            });  
	});*/

});

function aplicarMascaraExame(input) {
	// Verifica se o campo existe
	
		input.maskMoney({
			thousands : '.',
			decimal : ','
		});
		input.maskMoney('mask');
}

function paginacao(direcao) {
	$.ajax({
		type : "GET",
		url : "agendamento/paginacao/" + direcao,
		success : function(response) {
			$("#divList").html(response);
		},
		error : function(e) {
			alert(e);
		}

	});
}