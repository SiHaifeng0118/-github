package com.example.map;

public class T4sDian {
    private int xuHao;
    private int tuPian;
    private String dianMin;
    private String dianHua;
    private String juLi;
    private String diDian;

    public T4sDian(int xuHao, int tuPian, String dianMin, String dianHua, String juLi, String diDian) {
        this.xuHao = xuHao;
        this.tuPian = tuPian;
        this.dianMin = dianMin;
        this.dianHua = dianHua;
        this.juLi = juLi;
        this.diDian = diDian;
    }

    public int getXuHao() {
        return xuHao;
    }

    public void setXuHao(int xuHao) {
        this.xuHao = xuHao;
    }

    public int getTuPian() {
        return tuPian;
    }

    public void setTuPian(int tuPian) {
        this.tuPian = tuPian;
    }

    public String getDianMin() {
        return dianMin;
    }

    public void setDianMin(String dianMin) {
        this.dianMin = dianMin;
    }

    public String getDianHua() {
        return dianHua;
    }

    public void setDianHua(String dianHua) {
        this.dianHua = dianHua;
    }

    public String getJuLi() {
        return juLi;
    }

    public void setJuLi(String juLi) {
        this.juLi = juLi;
    }

    public String getDiDian() {
        return diDian;
    }

    public void setDiDian(String diDian) {
        this.diDian = diDian;
    }
}
