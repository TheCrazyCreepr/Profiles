package com.arcturusmc.profiles.user;

import com.arcturusmc.profiles.Profiles;
import com.arcturusmc.profiles.ui.Badges;

import java.sql.*;
import java.util.UUID;

public class User {

    private Profiles profiles;

    private UUID uuid;
    private String bio, badge;

    public User(Profiles profiles, UUID uuid) throws SQLException {
        this.profiles = profiles;

        this.uuid = uuid;

        Connection sqlConnection = profiles.getSqlManager().getConnection();

        PreparedStatement preparedStatement = sqlConnection.prepareStatement("SELECT BIO, BADGE FROM player_data WHERE UUID = ?;");
        preparedStatement.setString(1, uuid.toString());
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()) {
            bio = rs.getString("BIO");
            badge = rs.getString("BADGE");
        } else {
            bio = null;
            badge = null;
            PreparedStatement preparedStatement1 = sqlConnection.prepareStatement("INSERT INTO player_data (UUID, BIO, BADGE) VALUES (?,?,?);");
            preparedStatement1.setString(1, uuid.toString());
            preparedStatement1.setString(2, bio);
            preparedStatement1.setString(3, badge);
            preparedStatement1.executeUpdate();
        }
    }

    public void setBio(String bio) {
        this.bio = bio;

        Connection sqlConnection = profiles.getSqlManager().getConnection();

        try {
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("UPDATE player_data SET BIO = ? WHERE UUID = ?;");
            preparedStatement.setString(1, bio);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public void setBadge(String badge) {
        this.badge = badge;

        Connection sqlConnection = profiles.getSqlManager().getConnection();

        try {
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("UPDATE player_data SET BADGE = ? WHERE UUID = ?;");
            preparedStatement.setString(1, badge);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public void clearBadge() {
        this.badge = null;
        Connection sqlConnection = profiles.getSqlManager().getConnection();

        try {
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("UPDATE player_data SET BADGE = ? WHERE UUID = ?;");
            preparedStatement.setNull(1, Types.VARCHAR);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();

        } catch (SQLException x) {
            x.printStackTrace();
        }
    }

    public void setBadge(Badges badge) {
        this.badge = badge.name();

        Connection sqlConnection = profiles.getSqlManager().getConnection();

        try {
            PreparedStatement preparedStatement = sqlConnection.prepareStatement("UPDATE player_data SET BADGE = ? WHERE UUID = ?;");
            preparedStatement.setString(1, badge.name());
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException x) {
            x.printStackTrace();
        }

    }

    public String getBio() {
        return bio;
    }

    public String getBadge() {
        return badge;
    }

    public String getBadgeDisplay() {
        Badges badgeDisplay = Badges.valueOf(badge);
        return badgeDisplay.getDisplay();
    }
}
