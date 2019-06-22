package Persistencia;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class UsuarioDAOJson implements UsuarioDAO{
    String localBD;

    public UsuarioDAOJson() {
        localBD = "./BancoDados/";
        File bd = new File(localBD);

        //Se a pasta do banco nao existe, criar.
        if (!bd.exists()){
            bd.mkdirs();
        }
        bd = new File(localBD + "usuarios.json");
        BufferedWriter bw;

        //Se o json de usuarios nao existe, criar.
        if (!bd.exists()) {
            try {
                bd.createNewFile();
                bw = new BufferedWriter(new FileWriter(bd));

                JSONObject us = new JSONObject();
                JSONArray jArr = new JSONArray();
                us.put("usuarios", jArr);
                bw.write(us.toJSONString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<UsuarioDTO> buscarTodos() {
        return null;
    }

    @Override
    public UsuarioDTO buscarPorCpf(String cpf) {
        return null;
    }

    @Override
    public void inserir(UsuarioDTO usuario) {
        String caminhoUsuarios = localBD + "usuarios.json";
        BufferedWriter bw;

        JSONObject usuarios;
        JSONArray jsArr;
        try {
            usuarios = (JSONObject) new JSONParser().parse(new FileReader(caminhoUsuarios));
            jsArr = (JSONArray) usuarios.get("usuarios");
            JSONObject out = new JSONObject();

            JSONObject usu = new JSONObject();
            usu.put("admin", usuario.isAdmin());
            usu.put("senha", usuario.getSenha());
            usu.put("email", usuario.getEmail());
            usu.put("cpf", usuario.getCpf());
            usu.put("nome", usuario.getNome());
            jsArr.add(usu);
            out.put("usuarios", jsArr);

            bw = new BufferedWriter(new FileWriter(caminhoUsuarios));
            bw.write(out.toJSONString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(UsuarioDTO usuario) {

    }
}
