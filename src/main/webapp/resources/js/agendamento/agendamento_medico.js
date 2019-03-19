$(document)
		.ready(
				function() {

					criarCalendario([]);

					$('.fc-v-event').on('click', function() {
						alert(this.val());
					});

					$('#selectMedico')
							.change(
									function() {
										var codigoMedico = $(this).val();
										$
												.ajax({
													type : "GET",
													url : "agendamentos/medico/"
															+ codigoMedico,
													success : function(response) {
														var eventos = response
																.map(function(e) {

																	return {
																		title : e.nomePaciente,
																		start : e.dataFinal,
																		end : e.dataFinalConsulta,
																		color : e.statusAgendamento,
																		id : e.id,
																	}
																})

														$(
																'.fullcalendar-external')
																.fullCalendar(
																		'removeEvents');
														$(
																'.fullcalendar-external')
																.fullCalendar(
																		'addEventSource',
																		eventos);
													},
													error : function(e) {
													}
												});
									});

					function criarCalendario(eventos) {

						// Event colors
						var eventColors = [ {
							title : 'All Day Event',
							start : '2018-12-20',
							color : '#EF5350'
						}, {
							title : 'Long Event',
							start : '2018-12-20',
							end : '2014-11-10',
							color : '#26A69A'
						}, {
							id : 999,
							title : 'Repeating Event',
							start : '2018-12-20T16:00:00',
							end : '2018-12-20T16:15:00',
							color : '#26A69A'
						} ];

						// External events
						// ------------------------------

						// Initialize the calendar
						$('.fullcalendar-external')
								.fullCalendar(
										{
											header : {
												left : 'prev,next today',
												center : 'title',
												right : 'agendaWeek'
											},
											buttonText : {
												agendaWeek : "Semana"
											},
											selectable : true,
											selectHelper : true,

											select : function(start, end,
													jsEvent, view) {
												var title = prompt('Nome do Paciente:');
												var eventData;

												if (title) {
													eventData = {
														start : start,
														end : end,
														title : title,
													};

													$('.fullcalendar-external')
															.fullCalendar(
																	'renderEvent',
																	eventData,
																	true); // stick?
													// =
													// true

													$
															.ajax({
																type : "POST",
																contentType : "application/json",
																url : "agendamento/incluir_horario",
																data : JSON
																		.stringify({
																			nomePaciente : title,
																			dataInicio : start,
																			datafim : end,
																			idMedico : $(
																					'#selectMedico')
																					.val(),
																		}),
																dataType : 'json',
																success : function(
																		eventData) {
																	alert("Agendamento cadastrado com sucesso.");
																},
																error : function(
																		e) {

																}
															});

												}

												$('.fullcalendar-external')
														.fullCalendar(
																'unselect');
											},

											defaultView : 'agendaWeek',
											locale : 'pt-br',
											contentHeight : 'auto',
											editable : true,
											defaultDate : $('#dataAtual').val(),
											events : eventos,
											lang : 'pt-br',

											eventDrop : function(event,
													dayDelta, minuteDelta,
													allDay, revertFunc) {

												if (confirm("Deseja mudar o horário?")) {

													$
															.ajax({
																type : "POST",
																contentType : "application/json",
																url : "agendamento/alterar_horario",
																data : JSON
																		.stringify({
																			idAgendamento : event.id,
																			nomePaciente : event.title,
																			dataInicio : event.start,
																			datafim : event.end,
																			idMedico : $(
																					'#selectMedico')
																					.val(),
																		}),
																dataType : 'json',
																success : function(
																		eventData) {
																	alert("Agendamento alterado com sucesso.");
																},
																error : function(
																		e) {

																}
															});
												}

											},

											droppable : true, // this allows
											// things to be
											// dropped onto
											// the
											// calendar
											drop : function() {
												if ($('#drop-remove').is(
														':checked')) { // is the "remove after
													// drop" checkbox checked?
													$(this).remove(); // if so, remove the element from the
													// "Draggable Events" list
												}
											}
										});

						// Initialize the external events
						$('#external-events .fc-event').each(function() {

							// Different colors for events
							$(this).css({
								'backgroundColor' : $(this).data('color'),
								'borderColor' : $(this).data('color')
							});

							// Store data so the calendar knows to render an event upon drop
							$(this).data('event', {
								title : $.trim($(this).html()), // use the element's text as the
								// event title
								color : $(this).data('color'),
								stick : true
							// maintain when user navigates (see docs on the renderEvent method)
							});

							// Make the event draggable using jQuery UI
							$(this).draggable({
								zIndex : 999,
								revert : true, // will cause the event to go back to its
								revertDuration : 0
							// original position after the drag
							});
						});
					}
				});
