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
        if (!bd.exists()){
            bd.mkdirs();
        }
    }

    @Override
    public ArrayList<UsuarioDTO> buscarTodos() {
        return null;
    }

    @Override
    public UsuarioDTO buscarPorId(String id) {
        return null;
    }

    @Override
    public void inserir(UsuarioDTO usuario) {
        String caminhoUsuarios = localBD + "usuarios.json";
        File json = new File(caminhoUsuarios);
        BufferedWriter bw;

        //Criar novo JSON
        if (!json.exists()) {
            try {
                json.createNewFile();
                bw = new BufferedWriter(new FileWriter(json));

                JSONObject us = new JSONObject();
                JSONArray jArr = new JSONArray();
                us.put("usuarios", jArr);
                bw.write(us.toJSONString());
                bw.close();
                System.out.println(us.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
