/*
This is free and unencumbered software released into the public domain.

Anyone is free to copy, modify, publish, use, compile, sell, or
distribute this software, either in source code form or as a compiled
binary, for any purpose, commercial or non-commercial, and by any
means.

In jurisdictions that recognize copyright laws, the author or authors
of this software dedicate any and all copyright interest in the
software to the public domain. We make this dedication for the benefit
of the public at large and to the detriment of our heirs and
successors. We intend this dedication to be an overt act of
relinquishment in perpetuity of all present and future rights to this
software under copyright law.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.

For more information, please refer to <http://unlicense.org>
 */
package br.estacio.prii.entidade;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Jogo {

    private Integer id;
    private Selecao selecaoA;
    private Selecao selecaoB;
    private Integer placarA;
    private Integer placarB;
    private LocalDateTime data = LocalDateTime.now();
    public enum tipoFase {
        Grupos, 
        Oitavas_de_Final,
        Quartas_de_Final,
        Semi_Final,
        Final;
    }
    private tipoFase fase;
    private String grupo;
    private Estadio estadio;

    public Jogo(Selecao selecaoA, Selecao selecaoB, tipoFase fase, String grupo, Estadio estadio) {
        this.selecaoA = selecaoA;
        this.selecaoB = selecaoB;
        this.fase = fase;
        this.grupo = grupo;
        this.estadio = estadio;
    }

    public Jogo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Selecao getSelecaoA() {
        return selecaoA;
    }

    public void setSelecaoA(Selecao selecaoA) {
        this.selecaoA = selecaoA;
    }

    public Selecao getSelecaoB() {
        return selecaoB;
    }

    public void setSelecaoB(Selecao selecaoB) {
        this.selecaoB = selecaoB;
    }

    public Integer getPlacarA() {
        return placarA;
    }

    public void setPlacarA(Integer placarA) {
        this.placarA = placarA;
    }

    public Integer getPlacarB() {
        return placarB;
    }

    public void setPlacarB(Integer placarB) {
        this.placarB = placarB;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getDataString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", data.getDayOfMonth()));
        sb.append("/");
        sb.append(String.format("%02d", data.getMonthValue()));
        sb.append("/");
        sb.append(String.format("%02d", data.getYear()));
        return sb.toString();
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void parseData(String data) throws Exception {
        try {
            this.data = LocalDateTime.parse(data);
        } catch (DateTimeParseException e) {
            throw new Exception(e.getMessage());
        }
    }

    public String getHorario() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", data.getHour()));
        sb.append(":");
        sb.append(String.format("%02d", data.getMinute()));
        sb.append(":");
        sb.append(String.format("%02d", data.getSecond()));
        return sb.toString();
    }

    public void setHorario(String horario) throws Exception {
        try {
            Integer hora = Integer.parseInt(horario.split(":")[0]);
            Integer minuto = Integer.parseInt(horario.split(":")[1]);
            Integer segundo = Integer.parseInt(horario.split(":")[2]);
            data = data.withHour(hora);
            data = data.withMinute(minuto);
            data = data.withSecond(segundo);
        } catch (NumberFormatException e) {
            throw new Exception("Erro ao converter horario: " + e.getMessage());
        }
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Estadio getEstadio() throws ItemNotFoundException {
        if (estadio == null){
            throw new ItemNotFoundException("Estádio não definido.");
        } else {
            return estadio;
        }
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

}
