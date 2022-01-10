package com.nash.product.entity.pojo;


public class CrustInfoPoJo {
    private int id;
    private String addr;
    private static final long serialVersionUID = 1L;
    public CrustInfoPoJo(String addr,String ip ,String smanager, String chain, String name, String ipfs, String hostIp, String hostName, String currStaking, String maxStaking, String pullque, String smalltaskque, String bigtaskque, String sealque, String block, String error) {
        this.addr = addr;
        this.ip = ip;
        this.smanager = smanager;
        this.chain = chain;
        this.name = name;
        this.ipfs = ipfs;
        this.hostIp = hostIp;
        this.hostName = hostName;
        this.currStaking = currStaking;
        this.maxStaking = maxStaking;
        this.pullque = pullque;
        this.smalltaskque = smalltaskque;
        this.bigtaskque = bigtaskque;
        this.sealque = sealque;
        this.block = block;
        this.error = error;
    }

    public void setCrustInfo (){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getSmanager() {
        return smanager;
    }

    public void setSmanager(String smanager) {
        this.smanager = smanager;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpfs() {
        return ipfs;
    }

    public void setIpfs(String ipfs) {
        this.ipfs = ipfs;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getCurrStaking() {
        return currStaking;
    }

    public void setCurrStaking(String currStaking) {
        this.currStaking = currStaking;
    }

    public String getMaxStaking() {
        return maxStaking;
    }

    public void setMaxStaking(String maxStaking) {
        this.maxStaking = maxStaking;
    }

    public String getPullque() {
        return pullque;
    }

    public void setPullque(String pullque) {
        this.pullque = pullque;
    }

    public String getSmalltaskque() {
        return smalltaskque;
    }

    public void setSmalltaskque(String smalltaskque) {
        this.smalltaskque = smalltaskque;
    }

    public String getBigtaskque() {
        return bigtaskque;
    }

    public void setBigtaskque(String bigtaskque) {
        this.bigtaskque = bigtaskque;
    }

    public String getSealque() {
        return sealque;
    }

    public void setSealque(String sealque) {
        this.sealque = sealque;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    private String smanager;
    private String chain;
    private String name;
    private String ipfs;
    private String hostIp;
    private String hostName;
    private String currStaking;
    private String maxStaking;
    private String pullque;
    private String smalltaskque;
    private String bigtaskque;
    private String sealque;
    private String block;
    private String error;
    private String ip;
}
