package model;

import BDD.BancoDdados;
import Objetos.consulta;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class consultaTableModel extends AbstractTableModel{
    
    private List<consulta> ConsultasTbl = new ArrayList<>();
    private String[] colunas = {"Paciente", "CPF", "Telefone", "Data", "Já era paciente?", "Consulta realizada"};
    SimpleDateFormat ft = new SimpleDateFormat("dd/mm/yyyy");

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return ConsultasTbl.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0:
                return ConsultasTbl.get(linha).getPaciente().getNome();
            case 1:
                return ConsultasTbl.get(linha).getPaciente().getCPF();
            case 2:
                return ConsultasTbl.get(linha).getPaciente().getTelefone();
            case 3:
                return ft.format(ConsultasTbl.get(linha).getData());
            case 4:
                return booleanConvert(ConsultasTbl.get(linha).isCliente());
            case 5:
                return booleanConvert(ConsultasTbl.get(linha).isRealizada());
        }
        return null;
    }
    
    public void alterar(int linha){
        this.ConsultasTbl.set(linha, BancoDdados.getConsulta());
        this.fireTableDataChanged();
    }
    
    public void adicionar(){
        this.ConsultasTbl.add(BancoDdados.getConsulta());
        this.fireTableDataChanged();
    }
    
    public void remove(int linha){
        this.ConsultasTbl.remove(linha);
        BancoDdados.remover(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public String booleanConvert(boolean retorno){
        if(retorno == true){
            return "Sim";
        }else{
            return "Não";
        }
    }
    
    public boolean isEmpty(){
       return ConsultasTbl.isEmpty();
    }
    
    public String getReceita(int linha){
        return ConsultasTbl.get(linha).getReceita();
    }
}
