package com.ktech.trail.couchdb;

import com.ktech.trail.core.model.entities.Artist;
import com.ktech.trail.core.model.values.Isni;
import com.ktech.trail.core.model.values.Name;
import org.ektorp.CouchDbConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CouchDbArtistWriterTest {
    @Test
    void givenConnector_whenSavingArtist_shouldInvokeUpdate() {
        CouchDbConnector mockedDbConnector = mock(CouchDbConnector.class);
        CouchDbArtistWriter writer = new CouchDbArtistWriter(mockedDbConnector);
        Artist artist = new Artist(new Name("Apple", "Inc."), new Isni("US0378331005"));

        writer.saveArtist(artist);

        verify(mockedDbConnector).update(Mockito.any());
    }
}
