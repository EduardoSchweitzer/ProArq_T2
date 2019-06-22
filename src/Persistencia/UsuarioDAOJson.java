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
            throw new UsuarioDAOException("CPF " + cpf + " nao econtrado");

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

    public JSONObject dtoParaJso(UsuarioDTO usuario) {
        JSONObject out = new JSONObject();

        out.put("admin", usuario.isAdmin());
        out.put("senha", usuario.getSenha());
        out.put("email", usuario.getEmail());
        out.put("cpf", usuario.getCpf());
        out.put("nome", usuario.getNome());

        return out;
    }

    @Override
    public void inserir(UsuarioDTO usuario) throws UsuarioDAOException {
        BufferedWriter bw;

        if (buscarPorCpf(usuario.getCpf()) == null) {
            throw new UsuarioDAOException("CPF" + usuario.getCpf() + " ja existe.");
        }

        JSONObject usuarios;
        JSONArray jsArr;
        try {
            usuarios = (JSONObject) new JSONParser().parse(new FileReader(caminhoUsuarios));
            jsArr = (JSONArray) usuarios.get("usuarios");
            JSONObject out = new JSONObject();
            JSONObject usu = dtoParaJso(usuario);

            jsArr.add(usu);
            out.put("usuarios", jsArr);

            bw = new BufferedWriter(new FileWriter(caminhoUsuarios));
            bw.write(out.toJSONString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new UsuarioDAOException("Falha ao inserir." + e.getMessage());
        }
    }

    @Override
    public void alterar(UsuarioDTO usuario) throws UsuarioDAOException {
        JSONObject usuarios;
        JSONArray jsArr;
        BufferedWriter bw;
        boolean encontrado = false;
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
                    encontrado = true;
                }
            }

            if (!encontrado) {
                throw new UsuarioDAOException("");
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
            throw new UsuarioDAOException("CPF " + usuario.getCpf() + " nao econtrado");
        }
    }
}
