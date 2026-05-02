package com.tankOfTitans.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "casilla")
public class Casilla {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @Column(nullable = false)
	 private int posX;

	 @Column(nullable = false)
	 private int posY;

	 @Column(nullable = false)
	 private boolean transitable;

	 @ManyToOne
	 @JoinColumn(name = "mapa_id", nullable = false)
	 private Mapa mapa;
	 
	 public Casilla() {
		 
	 }

	 public Casilla(int posX, int posY, boolean transitable, Mapa mapa) {
	     this.posX = posX;
	     this.posY = posY;
	     this.transitable = transitable;
	     this.mapa = mapa;
	 }

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public int getPosX() {
		 return posX;
	 }

	 public void setPosX(int posX) {
		 this.posX = posX;
	 }

	 public int getPosY() {
		 return posY;
	 }

	 public void setPosY(int posY) {
		 this.posY = posY;
	 }

	 public boolean isTransitable() {
		 return transitable;
	 }

	 public void setTransitable(boolean transitable) {
		 this.transitable = transitable;
	 }

	 public Mapa getMapa() {
		 return mapa;
	 }

	 public void setMapa(Mapa mapa) {
		 this.mapa = mapa;
	 }
	 
	 
}
