<script lang="ts">
  // import { onMount } from 'svelte';
  interface Character {
    name: string;
    job: string;
    level: number;
    world: string;
  }

  let characters: Character[] | null = null;
  let loading = false;
  let error: string | null = null;

  async function fetchCharacters() {
    loading = true;
    error = null;
    try {
      const res = await fetch('http://localhost:5000/api/users/1/characters');
      if (!res.ok) {
        throw new Error(`HTTP error! status: ${res.status}`);
      }
      characters = await res.json();
    } catch (e: unknown) {
      if (e instanceof Error) {
        error = e.message;
      } else {
        error = String(e);
      }
      characters = null;
    } finally {
      loading = false;
    }
  }
</script>

<style>
  .container {
    display: flex;
    height: 100vh;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }
  .error {
    color: red;
    margin-top: 1rem;
  }
  .loading {
    margin-top: 1rem;
  }
</style>

<div class="container">
  <button on:click={fetchCharacters} class="bg-blue-600 text-white px-6 py-3 rounded hover:bg-blue-700 transition">캐릭터 연동</button>

  {#if loading}
    <div class="loading">로딩 중...</div>
  {/if}

  {#if error}
    <div class="error">에러: {error}</div>
  {/if}

  {#if characters}
    <pre class="mt-4 max-w-lg overflow-auto text-left bg-gray-100 p-4 rounded">{JSON.stringify(characters, null, 2)}</pre>
  {/if}
</div>
