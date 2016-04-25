package br.ufrj.agil.presentation;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ufrj.agil.model.Usuario;
import br.ufrj.agil.service.CadastroUsuarioService;
import br.ufrj.agil.util.LocalDateUtil;

@WebServlet(name="cadastroUsuarioServlet", urlPatterns={"/cadastroUsuario"})
public class CadastroUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 3582837149440571075L;
	private static final Logger LOGGER = LogManager.getLogger(CadastroUsuarioServlet.class);

	private CadastroUsuarioService service = new CadastroUsuarioService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nome", request.getParameter("nome"));
		request.setAttribute("endereco", request.getParameter("endereco"));
		request.setAttribute("dataDeNascimento", request.getParameter("dataDeNascimento"));
		try {
			String nome = request.getParameter("nome");
			String endereco = request.getParameter("endereco");
			String strDataDeNascimento = request.getParameter("dataDeNascimento");
			LocalDate dataDeNascimento = LocalDateUtil.convert(strDataDeNascimento);
			
			Usuario usuario = new Usuario();
			usuario.setNome(nome);
			usuario.setEndereco(endereco);
			usuario.setDataDeNascimento(dataDeNascimento);
			this.service.cadastrar(usuario);
			request.setAttribute("msg", "Usuu&#225;rio cadastrado com sucesso.");
			request.setAttribute("msgLevel", "success");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("msgLevel", "danger");
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
