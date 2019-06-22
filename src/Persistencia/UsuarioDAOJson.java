package Persistencia;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class UsuarioDAOJson implements UsuarioDAO{
    String localBD;
    String caminhoUsuarios;

    public UsuarioDAOJson() {
        localBD = "./BancoDados/";
        caminhoUsuarios = localBD + "usuarios.json";

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
        ArrayList<UsuarioDTO> out = new ArrayList<>();
        JSONObject usuarios;
        JSONArray jsArr;
        try {
            usuarios = (JSONObject) new JSONParser().parse(new FileReader(caminhoUsuarios));
            jsArr = (JSONArray) usuarios.get("usuarios");
            JSONObject jAux;
            for (Object us : jsArr) {
                jAux = (JSONObject) us;
                    out.add(jsoParaDTO(jAux));
                }
            return out;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UsuarioDTO buscarPorCpf(String cpf) {
        JSONObject usuarios;
        JSONArray jsArr;
        try {
            usuarios = (JSONObject) new JSONParser().parse(new FileReader(caminhoUsuarios));
            jsArr = (JSONArray) usuarios.get("usuarios");
            JSONObject jAux;
            UsuarioDTO out = new UsuarioDTO();
            for (Object us : jsArr) {
                jAux = (JSONObject) us;
                if (cpf.matches((String)jAux.get("cpf"))) {
                    return jsoParaDTO(jAux);
                }
            }
            throw new Exception();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsuarioDTO jsoParaDTO(JSONObject jso){
        UsuarioDTO us = new UsuarioDTO();
        us.setSenha((String)jso.get("senha"));
        us.setCpf((String)jso.get("cpf"));
        us.setNome((String)jso.get("nome"));
        us.setAdmin((boolean)jso.get("admin"));
        us.setEmail((String)jso.get("email"));

        return us;
    }

    @Override
    public void inserir(UsuarioDTO usuario) {
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
        JSONObject usuarios;
        JSONArray jsArr;
        BufferedWriter bw;
        try {
            usuarios = (JSONObject) new JSONParser().parse(new FileReader(caminhoUsuarios));
            jsArr = (JSONArray) usuarios.get("usuarios");
            JSONObject jAux;
            JSONObject out = new JSONObject();
            for (Object us : jsArr) {
                jAux = (JSONObject) us;
                if (usuario.getCpf().matches((String)jAux.get("cpf"))) {
                    jAux.replace("nome", usuario.getNome());
                    jAux.replace("senha", usuario.getSenha());
                    jAux.replace("email", usuario.getEmail());
                    jAux.replace("admin", usuario.isAdmin());
                }
            }

            out.put("usuarios", jsArr);
            bw = new BufferedWriter(new FileWriter(caminhoUsuarios));
            bw.write(out.toJSONString());
            bw.close();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
