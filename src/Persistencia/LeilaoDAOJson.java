package Persistencia;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class LeilaoDAOJson implements LeilaoDAO {
    String localBD;
    String caminholeiloes;

    public LeilaoDAOJson() {
        localBD = "./BancoDados/";
        caminholeiloes = localBD + "leiloes.json";

        File bd = new File(localBD);

        //Se a pasta do banco nao existe, criar.
        if (!bd.exists()){
            bd.mkdirs();
        }
        bd = new File(localBD + "leiloes.json");
        BufferedWriter bw;

        //Se o json de leiloes nao existe, criar.
        if (!bd.exists()) {
            try {
                bd.createNewFile();
                bw = new BufferedWriter(new FileWriter(bd));

                JSONObject le = new JSONObject();
                JSONArray jArr = new JSONArray();
                le.put("leiloes", jArr);
                bw.write(le.toJSONString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayList<LeilaoDTO> buscarTodos() {
        ArrayList<LeilaoDTO> out = new ArrayList<>();
        JSONObject leiloes;
        JSONArray jsArr;
        try {
            leiloes = (JSONObject) new JSONParser().parse(new FileReader(caminholeiloes));
            jsArr = (JSONArray) leiloes.get("leiloes");
            JSONObject jAux;
            for (Object le : jsArr) {
                jAux = (JSONObject) le;
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
        return new ArrayList<>();
    }

    @Override
    public ArrayList<LeilaoDTO> buscarAtivos() {
        ArrayList<LeilaoDTO> out = new ArrayList<>();
        JSONObject leiloes;
        JSONArray jsArr;
        try {
            leiloes = (JSONObject) new JSONParser().parse(new FileReader(caminholeiloes));
            jsArr = (JSONArray) leiloes.get("leiloes");
            JSONObject jAux;
            for (Object le : jsArr) {
                jAux = (JSONObject) le;
                if ((boolean)jAux.get("ativo")) {
                    out.add(jsoParaDTO(jAux));
                }
            }
            return out;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public ArrayList<LeilaoDTO> buscarFinalizados() {
        ArrayList<LeilaoDTO> out = new ArrayList<>();
        JSONObject leiloes;
        JSONArray jsArr;
        try {
            leiloes = (JSONObject) new JSONParser().parse(new FileReader(caminholeiloes));
            jsArr = (JSONArray) leiloes.get("leiloes");
            JSONObject jAux;
            for (Object le : jsArr) {
                jAux = (JSONObject) le;
                if (!(boolean)jAux.get("ativo")) {
                    out.add(jsoParaDTO(jAux));
                }
            }
            return out;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public LeilaoDTO buscarPorId(String id) {
        JSONObject leiloes;
        JSONArray jsArr;
        try {
            leiloes = (JSONObject) new JSONParser().parse(new FileReader(caminholeiloes));
            jsArr = (JSONArray) leiloes.get("leiloes");
            JSONObject jAux;
            LeilaoDTO out = new LeilaoDTO();
            for (Object us : jsArr) {
                jAux = (JSONObject) us;
                if (id.matches((String)jAux.get("id"))) {
                    return jsoParaDTO(jAux);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private LeilaoDTO jsoParaDTO(JSONObject jso){
        LeilaoDTO out = new LeilaoDTO();
        out.setId((String)jso.get("id"));
        out.setCpfProponente((String)jso.get("cpfProponente"));
        out.setNomeProduto((String)jso.get("nomeProduto"));
        out.setPrecoInicial((double)jso.get("precoInicial"));
        out.setUltimaModificacao((long)jso.get("ultimaModificacao"));
        out.setAtivo((boolean)jso.get("ativo"));
        JSONObject maiorLance = (JSONObject)jso.get("maiorLance");
        out.setMaiorLance((double)maiorLance.get("valor"), (String)maiorLance.get("cpfUsuario"));
        JSONArray jsArr = (JSONArray)jso.get("historicoLances");

        JSONObject aux;
        for (Object o : jsArr) {
            aux = (JSONObject)o;
            out.addLance((double)aux.get("valor"), (String)aux.get("cpfUsuario"));
        }
        return out;
    }

    private JSONObject dtoParaJso(LeilaoDTO leilao) {
        JSONObject out = new JSONObject();

        JSONObject maiorLance = new JSONObject();
        maiorLance.put("cpfUsuario", leilao.getMaiorLance().getUsuario());
        maiorLance.put("valor", leilao.getMaiorLance().getValor());

        JSONObject aux;
        JSONArray historicoLances = new JSONArray();
        for (LeilaoDTO.LanceDTO lance : leilao.getHistoricoLance()) {
            aux = new JSONObject();
            aux.put("cpfUsuario", lance.getUsuario());
            aux.put("valor", lance.getValor());
            historicoLances.add(aux);
        }

        out.put("id", leilao.getId());
        out.put("cpfProponente", leilao.getCpfProponente());
        out.put("nomeProduto", leilao.getNomeProduto());
        out.put("ultimaModificacao", leilao.getUltimaModificacao());
        out.put("maiorLance", maiorLance);
        out.put("historicoLances", historicoLances);
        out.put("precoInicial", leilao.getPrecoInicial());
        out.put("ativo", leilao.isAtivo());

        return out;
    }

    @Override
    public void inserir(LeilaoDTO leilao) throws LeilaoDAOIdDuplicadoException {
        BufferedWriter bw;
        JSONObject le;
        JSONArray jsArr;
        try {
            if (buscarPorId(leilao.getId()) != null) {
                throw new LeilaoDAOIdDuplicadoException("Leilao id " + leilao.getId() + " ja existe");
            }

            le = (JSONObject) new JSONParser().parse(new FileReader(caminholeiloes));
            jsArr = (JSONArray) le.get("leiloes");
            JSONObject out = new JSONObject();

            JSONObject leil = dtoParaJso(leilao);
            jsArr.add(leil);
            out.put("leiloes", jsArr);

            bw = new BufferedWriter(new FileWriter(caminholeiloes));

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

    @Override
    public void alterar(LeilaoDTO leilao) throws LeilaoDAOIdInexistenteException {
        JSONObject leiloes;
        JSONArray jsArr;
        BufferedWriter bw;
        boolean encontrado = false;
        try {
            leiloes = (JSONObject) new JSONParser().parse(new FileReader(caminholeiloes));
            jsArr = (JSONArray) leiloes.get("usuarios");
            JSONObject jAux;
            JSONObject out = new JSONObject();
            for (Object us : jsArr) {
                jAux = (JSONObject) us;
                if (leilao.getId().matches((String)jAux.get("id"))) {
                    jsArr.remove(jAux);
                    jsArr.add(dtoParaJso(leilao));
                    encontrado = true;
                }
            }

            if (!encontrado) {
                throw new LeilaoDAOIdInexistenteException("Leilao id " + leilao.getId() + " nao econtrado");
            }

            out.put("leiloes", jsArr);
            bw = new BufferedWriter(new FileWriter(caminholeiloes));
            bw.write(out.toJSONString());
            bw.close();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new LeilaoDAOIdInexistenteException("Falha ao buscar leilao." + e.getMessage());
        }
    }
}
