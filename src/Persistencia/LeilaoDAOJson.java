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
        return null;
    }

    @Override
    public ArrayList<LeilaoDTO> buscarAtivos() {
        return null;
    }

    @Override
    public ArrayList<LeilaoDTO> buscarFinalizados() {
        return null;
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

    public LeilaoDTO jsoParaDTO(JSONObject jso){
        LeilaoDTO out = new LeilaoDTO();

        return out;
    }

    @Override
    public void inserir(LeilaoDTO leilao) {
        BufferedWriter bw;

        JSONObject le;
        JSONArray jsArr;
        try {
            le = (JSONObject) new JSONParser().parse(new FileReader(caminholeiloes));
            jsArr = (JSONArray) le.get("leiloes");
            JSONObject out = new JSONObject();

            JSONObject maiorLance = new JSONObject();
            maiorLance.put("idUsuario", leilao.getMaiorLanceDTO().getUsuario());
            maiorLance.put("valor", leilao.getMaiorLanceDTO().getValor());

            JSONObject aux;
            JSONArray historicoLances = new JSONArray();
            for (LeilaoDTO.LanceDTO lance : leilao.getHistoricoLanceDTOS()) {
                aux = new JSONObject();
                aux.put(lance.getUsuario(), lance.getValor());
                historicoLances.add(aux);
            }

            JSONObject leil = new JSONObject();
            leil.put("id", leilao.getId());
            leil.put("idProponente", leilao.getIdProponente());
            leil.put("nomeProduto", leilao.getNomeProduto());
            leil.put("ultimaModificacao", leilao.getUltimaModificacao());
            leil.put("maiorLance", maiorLance);
            leil.put("historicoLances", historicoLances);
            jsArr.add(leil);
            out.put("leiloes", jsArr);

            bw = new BufferedWriter(new FileWriter(caminholeiloes));
            bw.write(out.toJSONString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(LeilaoDTO leilao) {

    }
}
