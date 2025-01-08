package org.example.managers;

import com.datastax.oss.driver.api.core.CqlSession;
import org.example.config.CassandraSessionFactory;
import org.example.dao.*;
import org.example.mapper.LibraryMapper;
import org.example.mapper.LibraryMapperBuilder;
import org.example.model.*;

import java.util.UUID;

public class ZarzadcaWoluminu {

    private final CqlSession session;

    private final WoluminDao woluminDao;
    private final KsiazkaDao ksiazkaDao;
    private final BeletrystykaDao beletrystykaDao;
    private final NaukowaDao naukowaDao;
    private final CzasopismoDao czasopismoDao;

    public ZarzadcaWoluminu() {
        CassandraSessionFactory.initSession();
        this.session = CassandraSessionFactory.getSession();

        LibraryMapper mapper = new LibraryMapperBuilder(session).build();

        woluminDao = mapper.woluminDao();
        ksiazkaDao = mapper.ksiazkaDao();
        beletrystykaDao = mapper.beletrystykaDao();
        naukowaDao = mapper.naukowaDao();
        czasopismoDao = mapper.czasopismoDao();
    }

    public void dodajWolumin(Wolumin w) {
        woluminDao.save(w);
    }
    public Wolumin znajdzWolumin(UUID id) {
        return woluminDao.findById(id);
    }
    public void zaktualizujWolumin(Wolumin w) {
        woluminDao.update(w);
    }
    public void usunWolumin(Wolumin w) {
        woluminDao.delete(w);
    }

    public void dodajKsiazka(Ksiazka k) {
        ksiazkaDao.save(k);
    }
    public Ksiazka znajdzKsiazka(UUID id) {
        return ksiazkaDao.findById(id);
    }
    public void zaktualizujKsiazka(Ksiazka k) {
        ksiazkaDao.update(k);
    }
    public void usunKsiazka(Ksiazka k) {
        ksiazkaDao.delete(k);
    }

    public void dodajBeletrystyka(Beletrystyka b) {
        beletrystykaDao.save(b);
    }
    public Beletrystyka znajdzBeletrystyka(UUID id) {
        return beletrystykaDao.findById(id);
    }
    public void zaktualizujBeletrystyka(Beletrystyka b) {
        beletrystykaDao.update(b);
    }
    public void usunBeletrystyka(Beletrystyka b) {
        beletrystykaDao.delete(b);
    }

    public void dodajNaukowa(Naukowa n) {
        naukowaDao.save(n);
    }
    public Naukowa znajdzNaukowa(UUID id) {
        return naukowaDao.findById(id);
    }
    public void zaktualizujNaukowa(Naukowa n) {
        naukowaDao.update(n);
    }
    public void usunNaukowa(Naukowa n) {
        naukowaDao.delete(n);
    }

    public void dodajCzasopismo(Czasopismo c) {
        czasopismoDao.save(c);
    }
    public Czasopismo znajdzCzasopismo(UUID id) {
        return czasopismoDao.findById(id);
    }
    public void zaktualizujCzasopismo(Czasopismo c) {
        czasopismoDao.update(c);
    }
    public void usunCzasopismo(Czasopismo c) {
        czasopismoDao.delete(c);
    }

    public void close() {
        CassandraSessionFactory.closeSession();
    }
}
