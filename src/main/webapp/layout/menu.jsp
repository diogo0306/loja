<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/" var="raiz" />

<!-- Main navbar -->
<div class="navbar navbar-inverse">
	<div class="navbar-header">
		<a class="navbar-brand" href="index.html">São Gabriel</a>


		<ul class="nav navbar-nav visible-xs-block">
			<li><a data-toggle="collapse" data-target="#navbar-mobile"><i
					class="icon-tree5"></i></a></li>
			<li><a class="sidebar-mobile-main-toggle"><i
					class="icon-paragraph-justify3"></i></a></li>
		</ul>
	</div>


	<div class="navbar-collapse collapse" id="navbar-mobile">
		<ul class="nav navbar-nav">
			<li><a class="sidebar-control sidebar-main-toggle hidden-xs"><i
					class="icon-paragraph-justify3"></i></a></li>

			<!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="icon-git-compare"></i> <span
					class="visible-xs-inline-block position-right">Git updates</span> <span
					class="badge bg-warning-400">9</span>
			</a>

				<div class="dropdown-menu dropdown-content">
					<div class="dropdown-content-heading">
						Git updates
						<ul class="icons-list">
							<li><a href="#"><i class="icon-sync"></i></a></li>
						</ul>
					</div>

					<ul class="media-list dropdown-content-body width-350">
						<li class="media">
							<div class="media-left">
								<a href="#"
									class="btn border-primary text-primary btn-flat btn-rounded btn-icon btn-sm"><i
									class="icon-git-pull-request"></i></a>
							</div>

							<div class="media-body">
								Drop the IE <a href="#">specific hacks</a> for temporal inputs
								<div class="media-annotation">4 minutes ago</div>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<a href="#"
									class="btn border-warning text-warning btn-flat btn-rounded btn-icon btn-sm"><i
									class="icon-git-commit"></i></a>
							</div>

							<div class="media-body">
								Add full font overrides for popovers and tooltips
								<div class="media-annotation">36 minutes ago</div>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<a href="#"
									class="btn border-info text-info btn-flat btn-rounded btn-icon btn-sm"><i
									class="icon-git-branch"></i></a>
							</div>

							<div class="media-body">
								<a href="#">Chris Arney</a> created a new <span
									class="text-semibold">Design</span> branch
								<div class="media-annotation">2 hours ago</div>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<a href="#"
									class="btn border-success text-success btn-flat btn-rounded btn-icon btn-sm"><i
									class="icon-git-merge"></i></a>
							</div>

							<div class="media-body">
								<a href="#">Eugene Kopyov</a> merged <span class="text-semibold">Master</span>
								and <span class="text-semibold">Dev</span> branches
								<div class="media-annotation">Dec 18, 18:36</div>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<a href="#"
									class="btn border-primary text-primary btn-flat btn-rounded btn-icon btn-sm"><i
									class="icon-git-pull-request"></i></a>
							</div>

							<div class="media-body">
								Have Carousel ignore keyboard events
								<div class="media-annotation">Dec 12, 05:46</div>
							</div>
						</li>
					</ul>

					<div class="dropdown-content-footer">
						<a href="#" data-popup="tooltip" title="All activity"><i
							class="icon-menu display-block"></i></a>
					</div>
				</div></li> -->
		</ul>

		<!-- <p class="navbar-text">
			<span class="label bg-success-400">Online</span>
		</p> -->

		<ul class="nav navbar-nav navbar-right">

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"> <i class="icon-bubbles4"></i> <span
					class="visible-xs-inline-block position-right">Solicitações</span>
					<span class="badge bg-warning-400">2</span>
			</a>

				<div class="dropdown-menu dropdown-content width-350">
					<div class="dropdown-content-heading">Solicitações</div>

					<ul class="media-list dropdown-content-body">
						<li class="media">
							<div class="media-left">
								<img src="assets/images/placeholder.jpg"
									class="img-circle img-sm" alt=""> <span
									class="badge bg-danger-400 media-badge">5</span>
							</div>

							<div class="media-body">
								<a href="#" class="media-heading"> <span
									class="text-semibold">James Alexander</span> <span
									class="media-annotation pull-right">04:58</span>
								</a> <span class="text-muted">who knows, maybe that would be
									the best thing for me...</span>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<img src="assets/images/placeholder.jpg"
									class="img-circle img-sm" alt=""> <span
									class="badge bg-danger-400 media-badge">4</span>
							</div>

							<div class="media-body">
								<a href="#" class="media-heading"> <span
									class="text-semibold">Margo Baker</span> <span
									class="media-annotation pull-right">12:16</span>
								</a> <span class="text-muted">That was something he was
									unable to do because...</span>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<img src="assets/images/placeholder.jpg"
									class="img-circle img-sm" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-heading"> <span
									class="text-semibold">Jeremy Victorino</span> <span
									class="media-annotation pull-right">22:48</span>
								</a> <span class="text-muted">But that would be extremely
									strained and suspicious...</span>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<img src="assets/images/placeholder.jpg"
									class="img-circle img-sm" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-heading"> <span
									class="text-semibold">Beatrix Diaz</span> <span
									class="media-annotation pull-right">Tue</span>
								</a> <span class="text-muted">What a strenuous career it is
									that I've chosen...</span>
							</div>
						</li>

						<li class="media">
							<div class="media-left">
								<img src="assets/images/placeholder.jpg"
									class="img-circle img-sm" alt="">
							</div>
							<div class="media-body">
								<a href="#" class="media-heading"> <span
									class="text-semibold">Richard Vango</span> <span
									class="media-annotation pull-right">Mon</span>
								</a> <span class="text-muted">Other travelling salesmen live
									a life of luxury...</span>
							</div>
						</li>
					</ul>

					<div class="dropdown-content-footer">
						<a href="#" data-popup="tooltip" title="All messages"><i
							class="icon-menu display-block"></i></a>
					</div>
				</div></li>

			<li class="dropdown dropdown-user"><a class="dropdown-toggle"
				data-toggle="dropdown"> <img src="assets/images/placeholder.jpg"
					alt=""> <span>${usuarioLogado.login }</span> <i class="caret"></i>
			</a>

				<ul class="dropdown-menu dropdown-menu-right">
					<!-- <li><a href="#"><i class="icon-user-plus"></i> My profile</a></li>
					<li><a href="#"><i class="icon-coins"></i> My balance</a></li>
					<li><a href="#"><span class="badge bg-teal-400 pull-right">58</span>
							<i class="icon-comment-discussion"></i> Messages</a></li>
					<li class="divider"></li>
					<li><a href="#"><i class="icon-cog5"></i> Account settings</a></li> -->
					<li><a href="${raiz}logout"><i class="icon-switch2"></i>Sair</a></li>
				</ul></li>
		</ul>
	</div>
</div>
<!-- /main navbar -->

<!-- Page content -->
<div class="page-content">
	<div class="sidebar sidebar-main">
		<div class="sidebar-content">

			<!-- User menu -->
			<div class="sidebar-user">
				<div class="category-content">
					<div class="media">
						<a href="#" class="media-left"><img
							src="assets/images/placeholder.jpg" class="img-circle img-sm"
							alt=""></a>
						<div class="media-body">
							<span class="media-heading text-semibold">${usuarioLogado.nome }</span>
							<div class="text-size-mini text-muted">
								&nbsp;${usuarioLogado.email}</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /user menu -->

			<!-- Main navigation -->
			<div class="sidebar-category sidebar-category-visible"
				style="height: 100vh;">
				<div class="category-content no-padding">
					<ul class="navigation navigation-main navigation-accordion">
						<!-- Main -->
						<li class="navigation-header"><span>Menu</span> <i
							class="icon-menu" title="Main pages"></i></li>
						<li><a href="${raiz}"><i class="icon-home4"></i> <span>Dashboard</span></a></li>
						<li><a href="#"><i class="icon-stack4"></i> <span>Gerenciamento
									de Vendas</span></a>
							<ul>
								<li><a href="${raiz}vendas">Painel de Venda</a></li>
							</ul></li>
						<li><a href="#"><i class="icon-stack2"></i> <span>Cadastros</span></a>
							<ul>

								<li><a href="${raiz}usuariosLoja">Usuários</a></li>
								<li><a href="${raiz}produtos">Produtos</a></li>
								<li><a href="${raiz}clientesLoja">Clientes</a></li>

								<%-- <li><a href="${raiz}contratos">Contratos</a></li> --%>
								<%-- <li><a href="${raiz}planos">Planos</a></li> --%>
								<%-- <li><a href="${raiz}grupos">Grupos/Procedimentos</a></li> --%>
								<%-- <li><a href="${raiz}exames">Exames</a></li>	 --%>
								<%-- <li><a href="${raiz}clientes">Unidades</a></li> --%>
								<%-- <li><a href="${raiz}hospitais">Hospitais</a></li>
								<li><a href="${raiz}fornecedores">Fornecedores</a></li>
								<li><a href="${raiz}pacientes">Beneficiários</a></li>							
								<li><a href="${raiz}representantes">Representantes</a></li>
								<li><a href="${raiz}credenciados">Credenciados</a></li>
								<li><a href="${raiz}supervisores">Supervisores</a></li> --%>
							</ul></li>



						<!--	<li><a href="#"><i class="icon-stack3"></i> <span>Exames
									- Procedimentos</span></a>
							<ul>
								<li><a href="${raiz}tabelas">Tabelas de Preço</a></li>
								<li><a href="${raiz}exames">Exames/Procedimentos</a></li>
								<li><a href="${raiz}beneficiarioExames">Beneficiário/Exames</a></li>
							</ul></li> -->
						<!--  	 <li><a href="#"><i class="icon-stack3"></i> <span>Consultas
									- Agendamentos</span></a>
							<ul>
								<li><a href="${raiz}consultas">Consulta</a></li>
								<li><a href="${raiz}beneficiarioExames">Exames</a></li>
								<li><a href="${raiz}agendamentos">Agendamento</a></li>
								<li><a href="${raiz}agendamento">Consultas Por Médico</a></li>
								<li><a href="${raiz}calendarios">Agendamento - SMEC</a></li>
							</ul></li> -->
						<!-- 
						<li><a href="#"><i class="icon-stack4"></i> <span>Agendamento</span></a>
							<ul>
								<li><a href="${raiz}calendarios">Calendário</a></li>
								<li><a href="${raiz}consultas">Consultas/Exames</a></li>
								<li><a href="${raiz}agendamentos">Agendamentos</a></li>

							</ul></li>
						 -->
						<%-- <li><a href="#"><i class="icon-droplet2"></i> <span>Aprovações</span></a>
							<ul>
								<li><a href="${raiz}solicitacoes/exame">Exames</a></li>
								<li><a href="${raiz}solicitacoes/consulta">Consultas</a></li>
								<li><a href="${raiz}solicitacoes">Solicitações</a></li>
							</ul></li>
							
						<li><a href="#"><i class="icon-droplet2"></i> <span>Autorização</span></a>
							<ul>
								<li><a href="${raiz}autorizacoes">Autorizações</a></li>								
							</ul></li>
						<li><a href="#"><i class="icon-stack"></i> <span>Financeiro</span></a>
							<ul>
								<li><a href="${raiz}contas_receber">Contas a Receber</a></li>
								<li><a href="${raiz}contas_pagar">Contas a Pagar</a></li>
								<li><a href="${raiz}pagamentos">Pagamentos de Consultas</a></li>
								<li><a href="${raiz}contratosAtivos">Contratos Ativos</a></li>
								<li><a href="${raiz}baixascobranca">Baixa de Cobrança</a></li>
								<li><a href="${raiz}cancelamentosbaixa">Cancelamento de
										Baixa</a></li>
								<li><a href="${raiz}cancelamentoscobranca">Cancelamento
										de Cobrança</a></li>
								<li><a href="${raiz}cancelamentosguia">Cancelamento de
										Guias</a></li>
								<li><a href="${raiz}formaspagamentobalcao">Cop. via
										Balcão</a></li>
							</ul></li> --%>

						<li><a href="#"><i class="icon-list-unordered"></i> <span>Relatórios</span></a>
							<ul>
								<li><a href="${raiz}relatorio/autorizacoes">Autorizações</a></li>
								<%-- <li><a href="${raiz}relatorio/contas-pagar">Contas a Pagar</a></li>
								<li><a href="${raiz}relatorio/contas-receber">Contas a Receber</a></li> --%>
							</ul></li>
					</ul>
				</div>
			</div>
			<!-- /main navigation -->
		</div>
	</div>